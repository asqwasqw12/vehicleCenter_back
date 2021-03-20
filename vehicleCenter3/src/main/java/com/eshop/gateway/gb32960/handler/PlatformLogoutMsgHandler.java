package com.eshop.gateway.gb32960.handler;

import org.springframework.stereotype.Component;

import com.eshop.gateway.gb32960.pojo.req.PlatformLogoutMsg;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ChannelHandler.Sharable
public class PlatformLogoutMsgHandler extends BaseHandler<PlatformLogoutMsg>{
	
	    @Override
	    protected void channelRead0(ChannelHandlerContext ctx, PlatformLogoutMsg msg) throws Exception {
	        log.debug(msg.toString());
	        
	        //客户端平台登出，关闭连接
	        log.warn("客户端平台{}登出，关闭连接", ctx.channel().remoteAddress());
	        ctx.close();
	    }

	   
}
