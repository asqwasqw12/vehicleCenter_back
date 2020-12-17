package com.eshop.gateway.gb32960.pojo.req;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.eshop.gateway.gb32960.config.gb32960Const;
import com.eshop.gateway.gb32960.pojo.DataPacket;

import io.netty.buffer.ByteBuf;

public class VehicleLoginMsg extends DataPacket{
	
	private ZonedDateTime sampleTime;//采样时间
	private short flowId;   //流水号    
    private  String iccid;//ICCID    
    private byte count;//可充电储能子系统数   
    private byte length;//可充电子系统编码长度  
    private List<String> codes; //可充电储能系统编码集合,长度等于count x length
    
    public void setSampleTime(ZonedDateTime sampleTime) {
    	this.sampleTime=sampleTime;
    }
	
    public ZonedDateTime getSampleTime() {
    	return sampleTime;
    }
    
    public void setFlowId(short flowId) {
    	this.flowId = flowId;
    }
    
    public short getFlowId() {
    	return flowId;
    }
    
    public void setIccid(String iccid) {
    	this.iccid = iccid;
    }
    
    public String getIccid() {
    	return iccid;
    }
    
    public void setLength(byte length) {
    	 this.length = length;
    }
    
    public byte getLength() {
    	return length;
    }

    public void setCount(byte count) {
    	this.count = count;
    }
    
    public byte getCount() {
    	return count;
    }
    
    public void setCodes(List<String> codes) {
    	 this.codes = codes;
    }
    
    
    public List<String> getCodes(){
    	return codes;
    }
    
    public VehicleLoginMsg(ByteBuf byteBuf) {
    	super(byteBuf);
    }
    
    @Override
    public void parseBody() {
    	this.sampleTime = ZonedDateTime.of((this.payload.readByte()+ 2000),this.payload.readByte(),this.payload.readByte(),
        		this.payload.readByte(),this.payload.readByte(),this.payload.readByte(),0,gb32960Const.ZONE_UTC8);
    	this.flowId = this.payload.readShort();
    	this.iccid = this.payload.readCharSequence(20, gb32960Const.ASCII_CHARSET).toString();
    	this.count = this.payload.readByte();
    	this.length = this.payload.readByte();
    	if(this.count>0 && this.length>0) {
    		List<String> codeList = new ArrayList<>();
    		for(int i=0;i<this.count;i++) {
    			String code = this.payload.readCharSequence(this.length, gb32960Const.ASCII_CHARSET).toString();
    			this.codes.add(code);
    		}
    		this.codes = codeList;
    	}
    }
}
