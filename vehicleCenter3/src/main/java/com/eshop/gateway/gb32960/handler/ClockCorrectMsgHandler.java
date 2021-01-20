package com.eshop.gateway.gb32960.handler;

import org.springframework.stereotype.Component;

import com.eshop.gateway.gb32960.pojo.req.ClockCorrectMsg;
import com.eshop.gateway.gb32960.res.ClockCorrectRespMsg;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ChannelHandler.Sharable
public class ClockCorrectMsgHandler extends BaseHandler<ClockCorrectMsg>{
	@Override
    protected void channelRead0(ChannelHandlerContext ctx, ClockCorrectMsg msg) throws Exception {
	ClockCorrectRespMsg resp = ClockCorrectRespMsg.success(msg);
	write(ctx,resp);
}

}