package com.eshop.jt808.pojo;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.eshop.jt808.config.JT808Const;
import com.eshop.jt808.util.BCD;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.ReferenceCountUtil;

public class DataPacket {
	 protected DataHeader header = new DataHeader(); //消息头
	 protected ByteBuf payload; //消息体
	 
	 public void setHeader(DataHeader header) {
		 this.header = header;
	 }
	 
	 public DataHeader getHeader() {
		 return this.header;
	 }
	 
	 public void setPayload(ByteBuf payload) {
		 this.payload = payload;
	 }
	 
	 public ByteBuf getPayload() {
		 return this.payload;
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
	            if (this.header.getMsgBodyLength() != this.payload.readableBytes()) {
	                throw new RuntimeException("包体长度有误");
	            }
	            this.parseBody();
	        }finally {
	            ReferenceCountUtil.safeRelease(this.payload);
	        }
	    }
	 
	 protected void parseHead() {
	        header.setMsgId(payload.readShort());
	        header.setMsgBodyProps(payload.readShort());
	        header.setTerminalPhone(BCD.BCDtoString(readBytes(6)));
	        header.setFlowId(payload.readShort());
	        if (header.hasSubPackage()) {
	            //TODO 处理分包
	            payload.readInt();
	        }
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
	        ByteBuf bb = ByteBufAllocator.DEFAULT.heapBuffer();//在JT808Encoder escape()方法处回收
	        bb.writeInt(0);//先占4字节用来写msgId和msgBodyProps，JT808Encoder中覆盖回来
	        bb.writeBytes(BCD.toBcdBytes(StringUtils.leftPad(this.header.getTerminalPhone(), 12, "0")));
	        bb.writeShort(this.header.getFlowId());
	        //TODO 处理分包
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
	     * 从ByteBuf中读出固定长度的数组 ，根据808默认字符集构建字符串
	     * @param length
	     * @return
	     */
	    public String readString(int length) {
	    	System.out.println("length:"+length);
	    	byte[] bytes = { 49,50,51,52,53};
	    	System.out.println("bytes:"+Arrays.toString(bytes));
	    	byte[] byteSuccess = "success".getBytes();
	    	System.out.println("byteSuccess:"+Arrays.toString(byteSuccess));
	    	String strSuccess =new String(byteSuccess,JT808Const.DEFAULT_CHARSET);
	    	System.out.println("strSuccess:"+strSuccess);
	    	String str = new String(bytes,JT808Const.DEFAULT_CHARSET);
	    	System.out.println("test:"+str);
	       return new String(readBytes(length),JT808Const.DEFAULT_CHARSET);
	    }
}
