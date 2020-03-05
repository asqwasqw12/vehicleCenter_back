package com.eshop.jt808.codec.encode;

import com.eshop.jt808.config.JT808Const;
import com.eshop.jt808.pojo.DataPacket;
import com.eshop.jt808.util.JT808Util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class JT808Encoder extends MessageToByteEncoder<DataPacket>{
	
	 @Override
	    protected void encode(ChannelHandlerContext ctx, DataPacket msg, ByteBuf out) throws Exception {
	        //log.debug(msg.toString());
		    System.out.println("msg.toString="+msg.toString());
	        ByteBuf bb = msg.toByteBufMsg();
	        bb.markWriterIndex();//标记一下，先到前面去写覆盖的，然后回到标记写校验码
	        short bodyLen = (short) (bb.readableBytes() - 12);//包体长度=总长度-头部长度
	        short bodyProps = createDefaultMsgBodyProperty(bodyLen);
	        //覆盖占用的4字节
	        bb.writerIndex(0);
	        bb.writeShort(msg.getHeader().getMsgId());
	        bb.writeShort(bodyProps);
	        bb.resetWriterIndex();
	        bb.writeByte(JT808Util.XorSumBytes(bb));
	        //log.debug(">>>>> ip:{},hex:{}\n", ctx.channel().remoteAddress(), ByteBufUtil.hexDump(bb));
	        System.out.println("ip:"+ctx.channel().remoteAddress()+"hex:"+ByteBufUtil.hexDump(bb));
	        ByteBuf escape = escape(bb);
	        out.writeBytes(escape);
	        ReferenceCountUtil.safeRelease(escape);
	    }

	    /**
	     * 转义待发送数据
	     *
	     * @param raw
	     * @return
	     */
	    public ByteBuf escape(ByteBuf raw) {
	        int len = raw.readableBytes();
	        ByteBuf buf = ByteBufAllocator.DEFAULT.directBuffer(len + 12);
	        buf.writeByte(JT808Const.PKG_DELIMITER);
	        while (len > 0) {
	            byte b = raw.readByte();
	            if (b == 0x7e) {
	                buf.writeByte(0x7d);
	                buf.writeByte(0x02);
	            } else if (b == 0x7d) {
	                buf.writeByte(0x7d);
	                buf.writeByte(0x01);
	            } else {
	                buf.writeByte(b);
	            }
	            len--;
	        }
	        ReferenceCountUtil.safeRelease(raw);
	        buf.writeByte(JT808Const.PKG_DELIMITER);
	        return buf;
	    }

	    /**
	     * 生成header中的消息体属性
	     *
	     * @param bodyLen
	     * @return
	     */
	    public static short createDefaultMsgBodyProperty(short bodyLen) {
	        return createMsgBodyProperty(bodyLen, (byte) 0, false, (byte) 0);
	    }

	    public static short createMsgBodyProperty(short bodyLen, byte encType, boolean isSubPackage, byte reversed) {
	        int subPkg = isSubPackage ? 1 : 0;
	        int ret = (bodyLen & 0x3FF) | ((encType << 10) & 0x1C00) | ((subPkg << 13) & 0x2000)
	                | ((reversed << 14) & 0xC000);
	        return (short) (ret & 0xffff);
	    }


}
