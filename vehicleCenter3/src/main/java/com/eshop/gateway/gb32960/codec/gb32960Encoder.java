package com.eshop.gateway.gb32960.codec;



import org.apache.log4j.Logger;

import com.eshop.common.ProtocolParseUtil;
import com.eshop.gateway.gb32960.pojo.GB32960DataPacket;
import com.eshop.modules.kafka.Producer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
public class gb32960Encoder extends MessageToByteEncoder<GB32960DataPacket>{
	   static Logger log = Logger.getLogger(Producer.class);
	 	@Override
	    protected void encode(ChannelHandlerContext ctx, GB32960DataPacket msg, ByteBuf out) throws Exception {
	        log.info(msg.toString());
		    System.out.println("encode.msg"+msg.toString());
	        ByteBuf bb = msg.toByteBufMsg();
	        bb.markReaderIndex();
	        bb.readShort();//读索引后移两位
	        bb.writeByte(ProtocolParseUtil.XorSumBytes(bb));
	        bb.resetReaderIndex();
	        log.info(">>>>> ip:{},hex:{}\n");
	        log.info(ctx.channel().remoteAddress());
	        log.info(ByteBufUtil.hexDump(bb));
	        //log.debug(">>>>> ip:{},hex:{}\n", ctx.channel().remoteAddress(), ByteBufUtil.hexDump(bb));
	        //System.out.println("encode>>>>>>>ip:"+ctx.channel().remoteAddress()+"hex:"+ByteBufUtil.hexDump(bb));
	        out.writeBytes(bb);
	        ReferenceCountUtil.safeRelease(bb);
	    }

}
