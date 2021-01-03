package com.eshop.gateway.gb32960.handler;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.eshop.gateway.gb32960.pojo.req.GB32960HeartBeatMsg;
import com.eshop.gateway.gb32960.res.HeartBeatRespMsg;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("gb32960Handler")
@ChannelHandler.Sharable
public class HeartBeatMsgHandler extends BaseHandler<GB32960HeartBeatMsg>{
	
	@Override
    protected void channelRead0(ChannelHandlerContext ctx, GB32960HeartBeatMsg msg) throws Exception {
		
		HeartBeatRespMsg resp = HeartBeatRespMsg.success(msg);
	    write(ctx,resp);
	}
}
