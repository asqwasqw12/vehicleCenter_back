package com.eshop.gateway.gb32960.pojo.req;

import java.time.ZonedDateTime;

import com.eshop.gateway.gb32960.config.gb32960Const;
import com.eshop.gateway.gb32960.pojo.DataPacket;

import io.netty.buffer.ByteBuf;

public class PlatformLoginMsg extends DataPacket{
	
	   //平台登入时间
	   private ZonedDateTime loginTime;

	    //登入流水号
	    private Integer flowId;

	    //平台用户名
	    private String userName;

	    //平台登入密码
	    private String password;

	    //加密规则
	    private Short encryptionType;
	    
	    public void setLoginTime(ZonedDateTime loginTime) {
	    	this.loginTime=loginTime;
	    }
		
	    public ZonedDateTime getLoginTime() {
	    	return loginTime;
	    }
	    
	    public Integer getFlowId() {
	        return flowId;
	    }

	    public void setFlowId(Integer flowId) {
	        this.flowId = flowId;
	    }

	    public String getUserName() {
	        return userName;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public Short getEncryptionType() {
	        return encryptionType;
	    }

	    public void setEncryptionType(Short encryptionType) {
	        this.encryptionType = encryptionType;
	    }
	    
	    public PlatformLoginMsg(ByteBuf byteBuf) {
	    	super(byteBuf);
	    }
	    
	    @Override
	    public void parseBody() {
	    	this.loginTime = ZonedDateTime.of((this.payload.readByte()+ 2000),this.payload.readByte(),this.payload.readByte(),
	        		this.payload.readByte(),this.payload.readByte(),this.payload.readByte(),0,gb32960Const.ZONE_UTC8);
	    	this.flowId = this.payload.readUnsignedShort();
	    	this.userName = this.payload.readCharSequence(12, gb32960Const.ASCII_CHARSET).toString();
	    	this.password = this.payload.readCharSequence(20, gb32960Const.ASCII_CHARSET).toString();
	    	this.encryptionType = this.payload.readUnsignedByte();
	    }
}
