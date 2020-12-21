package com.eshop.gateway.gb32960.pojo.req;

import java.time.ZonedDateTime;

import com.eshop.gateway.gb32960.config.gb32960Const;
import com.eshop.gateway.gb32960.pojo.DataPacket;

import io.netty.buffer.ByteBuf;

public class PlatformLogoutMsg extends DataPacket {
	//平台登出时间
	   private ZonedDateTime loginTime;

	    //登入流水号
	    private Integer flowId;

	    
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


	    public PlatformLogoutMsg(ByteBuf byteBuf) {
	    	super(byteBuf);
	    }
	    
	    @Override
	    public void parseBody() {
	    	this.loginTime = ZonedDateTime.of((this.payload.readByte()+ 2000),this.payload.readByte(),this.payload.readByte(),
	        		this.payload.readByte(),this.payload.readByte(),this.payload.readByte(),0,gb32960Const.ZONE_UTC8);
	    	this.flowId = this.payload.readUnsignedShort();
	    }

}
