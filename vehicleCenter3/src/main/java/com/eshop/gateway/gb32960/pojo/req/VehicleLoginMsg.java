package com.eshop.gateway.gb32960.pojo.req;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.eshop.gateway.gb32960.config.gb32960Const;
import com.eshop.gateway.gb32960.pojo.GB32960DataPacket;

import io.netty.buffer.ByteBuf;

public class VehicleLoginMsg extends GB32960DataPacket{
	
	private LocalDateTime loginTime;//采样时间
	private Integer flowId;   //流水号    
    private  String iccid;//ICCID    
    private Short count;//可充电储能子系统数   
    private Short length;//可充电子系统编码长度  
    private List<String> codes; //可充电储能系统编码集合,长度等于count x length
    
    public void setLoginTime(LocalDateTime loginTime) {
    	this.loginTime=loginTime;
    }
	
    public LocalDateTime getLoginTime() {
    	return loginTime;
    }
    
    public void setFlowId(Integer flowId) {
    	this.flowId = flowId;
    }
    
    public Integer getFlowId() {
    	return flowId;
    }
    
    public void setIccid(String iccid) {
    	this.iccid = iccid;
    }
    
    public String getIccid() {
    	return iccid;
    }
    
    public void setLength(Short length) {
    	 this.length = length;
    }
    
    public Short getLength() {
    	return length;
    }

    public void setCount(Short count) {
    	this.count = count;
    }
    
    public Short getCount() {
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
    	this.loginTime = ZonedDateTime.of((this.payload.readByte()+ 2000),this.payload.readByte(),this.payload.readByte(),
        		this.payload.readByte(),this.payload.readByte(),this.payload.readByte(),0,gb32960Const.ZONE_UTC8).toLocalDateTime();
    	this.flowId = this.payload.readUnsignedShort();
    	this.iccid = this.payload.readCharSequence(20, gb32960Const.ASCII_CHARSET).toString();
    	this.count = this.payload.readUnsignedByte();
    	this.length = this.payload.readUnsignedByte();
    	if(this.count>0 && this.length>0) {
    		List<String> codeList = new ArrayList<>();
    		for(int i=0;i<this.count;i++) {
    			String code = this.payload.readCharSequence(this.length, gb32960Const.ASCII_CHARSET).toString();
    			codeList.add(code);
    		}
    		this.codes = codeList;
    	}
    	
    }
    
    @Override
    public String toString() {
    	String codeList =null;
    	if(codes != null && codes.size()>0) {
    	   for(String code:codes) {
    		codeList+= code;
    	   }
    	}
    	return "loginTime="+loginTime.toString()+";flowId="+flowId+";iccid="+iccid+";count="+count+";length="+length+";codeList="+codeList;
    }
}
