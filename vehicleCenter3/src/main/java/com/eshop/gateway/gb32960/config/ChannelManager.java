package com.eshop.gateway.gb32960.config;

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
public class ChannelManager {
	
	//需不需要设计成单例模式，仍需要测试
	
	
	private static final AttributeKey<String> VEHICLE_IDENTIFICATION = AttributeKey.newInstance("vehicleIdentification");

    private ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private Map<String, ChannelId> channelIdMap = new ConcurrentHashMap<>();	//线程安全的Map
    
    //private Map<String, String> channelIdMap = new ConcurrentHashMap<>();	//线程安全的Map,<String,channel.id().asLongText()>

    private ChannelFutureListener remover = future -> {
        String vehicleId = future.channel().attr(VEHICLE_IDENTIFICATION).get();
        if (channelIdMap.get(vehicleId) == future.channel().id()) {
            channelIdMap.remove(vehicleId);
        }
    };
    public boolean add(String vin, Channel channel) {
        boolean added = channelGroup.add(channel);
        if (added) {
            if (channelIdMap.containsKey(vin)) {//替换
                Channel old = get(vin);
                old.closeFuture().removeListener(remover);
                old.close();
            }
            channel.attr(VEHICLE_IDENTIFICATION).set(vin);
            channel.closeFuture().addListener(remover);
            channelIdMap.put(vin, channel.id());
            System.out.println("vin="+vin+"channelId="+channel.id().asLongText());
            //channelIdMap.put(vehicleId, channel.id().asLongText());
        }
        return added;
    }

    public boolean remove(String vehicleId) {
    	channelIdMap.remove(vehicleId);
        return channelGroup.remove(get(vehicleId));
    }

    public Channel get(String vehicleId) {
        return channelGroup.find(channelIdMap.get(vehicleId));
    }

    public ChannelGroup getChannelGroup() {
        return channelGroup;
    }
    
    public Map<String, ChannelId> getChannelIdMap(){
    	return channelIdMap;
    }

}
