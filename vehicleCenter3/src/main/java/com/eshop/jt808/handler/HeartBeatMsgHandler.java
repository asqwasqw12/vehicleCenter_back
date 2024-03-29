package com.eshop.jt808.handler;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.eshop.jt808.pojo.req.HeartBeatMsg;
import com.eshop.jt808.pojo.res.CommonRespMsg;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

//@Description: 心跳消息->CommonResp

@Slf4j
@Component("jt808Handler")
@ChannelHandler.Sharable
public class HeartBeatMsgHandler extends BaseHandler<HeartBeatMsg> {
	
	@Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatMsg msg) throws Exception {
        log.debug(msg.toString());
        CommonRespMsg resp = CommonRespMsg.success(msg, getSerialNumber(ctx.channel()));
        write(ctx,resp);
    }

}
