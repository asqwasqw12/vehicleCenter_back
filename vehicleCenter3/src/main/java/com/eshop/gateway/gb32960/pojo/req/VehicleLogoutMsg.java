package com.eshop.gateway.gb32960.pojo.req;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.eshop.gateway.gb32960.config.gb32960Const;
import com.eshop.gateway.gb32960.pojo.DataPacket;

import io.netty.buffer.ByteBuf;

public class VehicleLogoutMsg extends DataPacket{
	private ZonedDateTime sampleTime;//采样时间
	private Integer flowId;   //流水号    
	public void setSampleTime(ZonedDateTime sampleTime) {
    	this.sampleTime=sampleTime;
    }
	
    public ZonedDateTime getSampleTime() {
    	return sampleTime;
    }
    
    public void setFlowId(Integer flowId) {
    	this.flowId = flowId;
    }
    
    public Integer getFlowId() {
    	return flowId;
    }
    
    public VehicleLogoutMsg(ByteBuf byteBuf) {
    	super(byteBuf);
    }
    
    @Override
    public void parseBody() {
    	this.sampleTime = ZonedDateTime.of((this.payload.readByte()+ 2000),this.payload.readByte(),this.payload.readByte(),
        		this.payload.readByte(),this.payload.readByte(),this.payload.readByte(),0,gb32960Const.ZONE_UTC8);
    	this.flowId = this.payload.readUnsignedShort();
    }
}
