package com.eshop.jt808.handler;

import org.springframework.stereotype.Component;

import com.eshop.jt808.pojo.req.RegisterMsg;
import com.eshop.jt808.pojo.res.RegisterRespMsg;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

//@Description: 注册消息->RegisterResp

@Slf4j
@Component
@ChannelHandler.Sharable
public class RegisterMsgHandler extends BaseHandler<RegisterMsg> {
	@Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterMsg msg) throws Exception {
		//log.debug(msg.toString());
    	System.out.println("RegisterMsgHandler.msg:"+msg.toString());
    	System.out.println("默认鉴权成功！！！！！！！");
        //默认鉴权成功
        RegisterRespMsg resp = RegisterRespMsg.success(msg, getSerialNumber(ctx.channel()));
        write(ctx,resp);
    }

}
