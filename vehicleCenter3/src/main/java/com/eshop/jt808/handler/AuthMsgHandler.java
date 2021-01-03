package com.eshop.jt808.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eshop.jt808.config.Channel808Manager;
import com.eshop.jt808.pojo.req.AuthMsg;
import com.eshop.jt808.pojo.res.CommonRespMsg;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
@ChannelHandler.Sharable
public class AuthMsgHandler extends BaseHandler<AuthMsg>{
	@Autowired
    private Channel808Manager channel808Manager;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AuthMsg msg) throws Exception {
        //log.debug(msg.toString());
        channel808Manager.add(msg.getHeader().getTerminalPhone(), ctx.channel());
        CommonRespMsg resp = CommonRespMsg.success(msg, getSerialNumber(ctx.channel()));
        write(ctx,resp);
    }

}
