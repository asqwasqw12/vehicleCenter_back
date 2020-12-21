package com.eshop.gateway.gb32960.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eshop.gateway.gb32960.pojo.req.PlatformLoginMsg;
import com.eshop.jt808.config.ChannelManager;
import com.eshop.jt808.pojo.req.AuthMsg;
import com.eshop.jt808.pojo.res.CommonRespMsg;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

@Component
@ChannelHandler.Sharable
public class PlatformLoginMsgHandler extends BaseHandler<PlatformLoginMsg>{
	@Autowired
    private ChannelManager channelManager;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PlatformLoginMsg msg) throws Exception {
        //log.debug(msg.toString());
        channelManager.add(msg.getHeader().getTerminalPhone(), ctx.channel());
        CommonRespMsg resp = CommonRespMsg.success(msg, getSerialNumber(ctx.channel()));
        write(ctx,resp);
    }

}
