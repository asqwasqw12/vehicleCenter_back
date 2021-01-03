package com.eshop.gateway.gb32960.codec;



import com.eshop.common.ProtocolParseUtil;
import com.eshop.gateway.gb32960.pojo.GB32960DataPacket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class gb32960Encoder extends MessageToByteEncoder<GB32960DataPacket>{
	
	 	@Override
	    protected void encode(ChannelHandlerContext ctx, GB32960DataPacket msg, ByteBuf out) throws Exception {
	        //log.debug(msg.toString());
		    System.out.println("encode.msg"+msg.toString());
	        ByteBuf bb = msg.toByteBufMsg();
	        bb.readShort();//读索引后移两位
	        bb.writeByte(ProtocolParseUtil.XorSumBytes(bb));
	        //log.debug(">>>>> ip:{},hex:{}\n", ctx.channel().remoteAddress(), ByteBufUtil.hexDump(bb));
	        System.out.println("ip:"+ctx.channel().remoteAddress()+"hex:"+ByteBufUtil.hexDump(bb));
	        out.writeBytes(bb);
	        ReferenceCountUtil.safeRelease(bb);
	    }

}
