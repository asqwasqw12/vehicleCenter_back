package com.eshop.gateway.gb32960.codec;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eshop.common.ProtocolParseUtil;
import com.eshop.gateway.gb32960.pojo.GB32960DataPacket;
import com.eshop.modules.kafka.Producer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class gb32960Encoder extends MessageToByteEncoder<GB32960DataPacket>{
	   //static Logger log = LoggerFactory.getLogger(Producer.class);
	 	@Override
	    protected void encode(ChannelHandlerContext ctx, GB32960DataPacket msg, ByteBuf out) throws Exception {
	        log.info(msg.toString());
	        ByteBuf bb = msg.toByteBufMsg();
	        bb.markReaderIndex();
	        bb.readShort();//读索引后移两位
	        bb.writeByte(ProtocolParseUtil.XorSumBytes(bb));
	        bb.resetReaderIndex();
	        log.debug(">>>>> ip:{},hex:{}\n", ctx.channel().remoteAddress(), ByteBufUtil.hexDump(bb));
	        out.writeBytes(bb);
	        ReferenceCountUtil.safeRelease(bb);
	    }

}
