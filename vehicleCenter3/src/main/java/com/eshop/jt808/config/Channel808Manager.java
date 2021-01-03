package com.eshop.jt808.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;

@Component
public class Channel808Manager {
	private static final AttributeKey<String> TERMINAL_PHONE = AttributeKey.newInstance("jt808TerminalPhone");

    private ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private Map<String, ChannelId> channelIdMap = new ConcurrentHashMap<>();	//线程安全的Map

    private ChannelFutureListener remover = future -> {
        String phone = future.channel().attr(TERMINAL_PHONE).get();
        if (channelIdMap.get(phone) == future.channel().id()) {
            channelIdMap.remove(phone);
        }
    };
    public boolean add(String terminalPhone, Channel channel) {
        boolean added = channelGroup.add(channel);
        if (added) {
            if (channelIdMap.containsKey(terminalPhone)) {//替换
                Channel old = get(terminalPhone);
                old.closeFuture().removeListener(remover);
                old.close();
            }
            channel.attr(TERMINAL_PHONE).set(terminalPhone);
            channel.closeFuture().addListener(remover);
            channelIdMap.put(terminalPhone, channel.id());
        }
        return added;
    }

    public boolean remove(String terminalPhone) {
    	channelIdMap.remove(terminalPhone);
        return channelGroup.remove(get(terminalPhone));
    }

    public Channel get(String terminalPhone) {
        return channelGroup.find(channelIdMap.get(terminalPhone));
    }

    public ChannelGroup getChannelGroup() {
        return channelGroup;
    }

}
