package com.eshop.jt808.server;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.eshop.jt808.codec.decode.JT808Decoder;
import com.eshop.jt808.codec.encode.JT808Encoder;
import com.eshop.jt808.config.JT808Const;
import com.eshop.jt808.handler.AuthMsgHandler;
import com.eshop.jt808.handler.CancellationMsgHandler;
import com.eshop.jt808.handler.HeartBeatMsgHandler;
import com.eshop.jt808.handler.LocationMsgHandler;
import com.eshop.jt808.handler.RegisterMsgHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.EventExecutorGroup;

@Component
public class JT808ChannelInitializer extends ChannelInitializer<SocketChannel> {
	
	@Value("${netty.read-timeout}")
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
        // jt808协议 包头最大长度16+ 包体最大长度1023+分隔符2+转义字符最大姑且算60 = 1100
        pipeline.addLast(
                new DelimiterBasedFrameDecoder(1100, Unpooled.copiedBuffer(new byte[]{JT808Const.PKG_DELIMITER}),  //分隔符解码器
                        Unpooled.copiedBuffer(new byte[]{JT808Const.PKG_DELIMITER, JT808Const.PKG_DELIMITER})));
        pipeline.addLast(new JT808Decoder());
        pipeline.addLast(new JT808Encoder());
        pipeline.addLast(heartBeatMsgHandler);
        pipeline.addLast(businessGroup, locationMsgHandler);//因为locationMsgHandler中涉及到数据库操作，所以放入businessGroup
        pipeline.addLast(authMsgHandler);
        pipeline.addLast(registerMsgHandler);
        pipeline.addLast(cancellationMsgHandler);

}
}
