package com.eshop.gateway.gb32960.handler;

import org.springframework.stereotype.Component;

import com.eshop.gateway.gb32960.pojo.req.PlatformLoginMsg;
import com.eshop.gateway.gb32960.res.PlatformLoginRespMsg;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

@Component
@ChannelHandler.Sharable
public class PlatformLoginMsgHandler extends BaseHandler<PlatformLoginMsg>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PlatformLoginMsg msg) throws Exception {
        //log.debug(msg.toString());
        String userName = msg.getUserName();
        String password = msg.getPassword();
        Boolean success = true;
        
        if(success) {
        	PlatformLoginRespMsg resp = PlatformLoginRespMsg.success(msg, getSerialNumber(ctx.channel()));
        	write(ctx,resp);
        }else {
        	PlatformLoginRespMsg resp = PlatformLoginRespMsg.failure(msg, getSerialNumber(ctx.channel()));
        	write(ctx,resp);
        }
        
    }

}
