package com.eshop.common;

import io.netty.buffer.ByteBuf;

public class ProtocolParseUtil {
	
	//根据byteBuf的readerIndex和writerIndex计算校验码
    // 校验码规则：从消息头开始，同后一字节异或，直到校验码前一个字节，占用 1 个字节
	 public static byte XorSumBytes(ByteBuf byteBuf) {
	        byte sum = byteBuf.getByte(byteBuf.readerIndex());
	        for (int i = byteBuf.readerIndex() + 1; i < byteBuf.writerIndex(); i++) {
	            sum = (byte) (sum ^ byteBuf.getByte(i));
	        }
	        return sum;
	    }

}
