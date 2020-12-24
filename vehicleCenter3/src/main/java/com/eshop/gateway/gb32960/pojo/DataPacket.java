package com.eshop.gateway.gb32960.pojo;

import com.eshop.gateway.gb32960.config.gb32960Const;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.ReferenceCountUtil;

public class DataPacket {
	
	protected DataHeader header ; //数据包头
	protected ByteBuf payload; //数据包
	
	public void setHeader(DataHeader header) {
		this.header = header;
	}
	
	public DataHeader getHeader() {
		return header;
	}
	
	public void setPayload(ByteBuf payload) {
		this.payload = payload;
	}
	
	public DataPacket() {
    }
 
 public DataPacket(ByteBuf payload) {
        this.payload = payload;
    }
	
	 public void parse() {
	        try{
	            this.parseHead();
	            //验证包体长度
	            if (this.header.getPayloadLength() != this.payload.readableBytes()) {
	                throw new RuntimeException("包体长度有误");
	            }
	            this.parseBody();
	        }finally {
	            ReferenceCountUtil.safeRelease(this.payload);
	        }
	    }
	 
	 protected void parseHead() {
	        header.setRequestType(payload.readUnsignedByte()); //读取命令标识
	        header.setResponseTag(payload.readUnsignedByte()); //读取应答标志
	        header.setVin(payload.readCharSequence(17, gb32960Const.ASCII_CHARSET).toString());  //读取vin码
	        header.setEncrypTionType(payload.readUnsignedByte()); //读取加密方式
	        header.setPayloadLength(payload.readUnsignedShort()); //读取数据包体
	    }
	 
	    /**
	     * 请求报文重写
	     */
	    protected void parseBody() {

	    }
	    
	    /**
	     * 响应报文重写 并调用父类
	     * @return
	     */
	    public ByteBuf toByteBufMsg() {
	        ByteBuf bb = ByteBufAllocator.DEFAULT.heapBuffer();//在gb32960Encoder escape()方法处回收
	        bb.writeShort(0x2323);//起始符
	        bb.writeByte(header.getRequestType());//命令标志
	        bb.writeByte(header.getResponseTag());//应答标志
	        bb.writeBytes(header.getVin().getBytes(gb32960Const.ASCII_CHARSET));//vin
	        bb.writeByte(header.getEncrypTionType());//加密方式
	        bb.writeShort(header.getPayloadLength());//长度
	        return bb;
	    }
	    
	    /**
	     * 从ByteBuf中read固定长度的数组,相当于ByteBuf.readBytes(byte[] dst)的简单封装
	     * @param length
	     * @return
	     */
	    public byte[] readBytes(int length) {
	        byte[] bytes = new byte[length];
	        this.payload.readBytes(bytes);
	        return bytes;
	    }

	    /**
	     * 从ByteBuf中读出固定长度的数组 ，根据gb32960默认字符集构建字符串
	     * @param length
	     * @return
	     */
	    public String readString(int length) {
	       return new String(readBytes(length),gb32960Const.ASCII_CHARSET);
	    }

}