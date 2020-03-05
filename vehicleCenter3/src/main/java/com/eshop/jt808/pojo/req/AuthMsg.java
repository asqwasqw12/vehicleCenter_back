package com.eshop.jt808.pojo.req;

import com.eshop.jt808.pojo.DataPacket;

import io.netty.buffer.ByteBuf;

//@Description:鉴权包
public class AuthMsg extends DataPacket {

	 private String authCode;//鉴权码
	 
	 public void setAuthCode(String authCode) {
		 this.authCode = authCode;
	 }
	 
	 public String getAuthCode() {
		 return this.authCode;
	 }
	 
	 public AuthMsg(ByteBuf byteBuf) {
	        super(byteBuf);
	    }
	 
	 @Override
	    public void parseBody() {
	        this.setAuthCode(readString(this.payload.readableBytes()));
	    }
}
