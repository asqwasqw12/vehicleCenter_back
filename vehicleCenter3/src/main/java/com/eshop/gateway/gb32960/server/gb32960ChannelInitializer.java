package com.eshop.gateway.gb32960.server;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.EventExecutorGroup;

@Component
public class gb32960ChannelInitializer extends ChannelInitializer<SocketChannel>{
	
	@Value("${netty.gb32960-read-timeout}")
    private int readTimeOut;

    @Autowired
    @Qualifier("businessGroup")
    private EventExecutorGroup businessGroup;

    @Autowired
    private AuthMsgHandler authMsgHandler;

    @Autowired
    private HeartBeatMsgHandler heartBeatMsgHandler;

    @Autowired
    private LocationMsgHandler locationMsgHandler;

    @Autowired
    private CancellationMsgHandler cancellationMsgHandler;

    @Autowired
    private RegisterMsgHandler registerMsgHandler;
    
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(
                new IdleStateHandler(readTimeOut, 0, 0, TimeUnit.MINUTES));   //心跳与重连
        // gb32960协议
        pipeline.addLast(new gb32960Decoder()); //解码器
        pipeline.addLast(new gb32960Encoder()); //编码器
        pipeline.addLast(heartBeatMsgHandler);
        pipeline.addLast(businessGroup, locationMsgHandler);//因为locationMsgHandler中涉及到数据库操作，所以放入businessGroup
        pipeline.addLast(authMsgHandler);
        pipeline.addLast(registerMsgHandler);
        pipeline.addLast(cancellationMsgHandler);

  }
}
