package com.eshop.gateway.gb32960.handler;


import org.springframework.stereotype.Component;

import com.eshop.gateway.gb32960.pojo.req.VehicleLogoutMsg;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ChannelHandler.Sharable
public class VehicleLogoutMsgHandler extends BaseHandler<VehicleLogoutMsg>{
	
	@Override
    protected void channelRead0(ChannelHandlerContext ctx, VehicleLogoutMsg msg) throws Exception {
        
        String vin = msg.getHeader().getVin();
        
        //客户端平台登出，关闭连接
        // log.warn("车辆{}登出，关闭连接", ctx.channel().remoteAddress());
    	System.out.println("车辆"+ctx.channel().remoteAddress()+"登出，关闭连接");
        ctx.close();
    }

}
