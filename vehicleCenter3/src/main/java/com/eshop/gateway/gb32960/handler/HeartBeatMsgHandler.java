package com.eshop.gateway.gb32960.handler;

import org.springframework.stereotype.Component;

import com.eshop.gateway.gb32960.pojo.req.HeartBeatMsg;
import com.eshop.gateway.gb32960.res.HeartBeatRespMsg;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ChannelHandler.Sharable
public class HeartBeatMsgHandler extends BaseHandler<HeartBeatMsg>{
	
	@Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatMsg msg) throws Exception {
		
		HeartBeatRespMsg resp = HeartBeatRespMsg.success(msg);
	write(ctx,resp);
	}
}
