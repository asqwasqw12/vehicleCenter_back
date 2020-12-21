package com.eshop.gateway.gb32960.pojo.req;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.eshop.gateway.gb32960.config.gb32960Const;
import com.eshop.gateway.gb32960.pojo.DataPacket;

import io.netty.buffer.ByteBuf;

public class QueryMsg extends DataPacket{
	
	private ZonedDateTime sampleTime;//参数查询时间
	
	private Short parameterCount; //参数总数
	
	private List<Short> parameterIds; //参数ID列表
	
	 public void setSampleTime(ZonedDateTime sampleTime) {
	    	this.sampleTime=sampleTime;
	    }
		
	    public ZonedDateTime getSampleTime() {
	    	return sampleTime;
	    }
	
    public QueryMsg(ByteBuf byteBuf) {
    	super(byteBuf);
    }
    
    public void setParameterCount(Short parameterCount) {
    	this.parameterCount = parameterCount;
    }
    
    public Short getParameterCount() {
    	return parameterCount;
    }
    
    public void setParameterIds(List<Short> parameterIds) {
    	this.parameterIds = parameterIds;
    }
    
    public List<Short> getParameterIds(){
    	return parameterIds;
    }
    
    
    @Override
    public void parseBody() {
    	
    	sampleTime = ZonedDateTime.of((this.payload.readByte()+ 2000),this.payload.readByte(),this.payload.readByte(),
        		this.payload.readByte(),this.payload.readByte(),this.payload.readByte(),0,gb32960Const.ZONE_UTC8);
    	parameterCount = payload.readUnsignedByte();
    	List<Short> list= new ArrayList<Short>();
    	for(int i=0;i<parameterCount;i++) {
    		list.add(payload.readUnsignedByte());
    	}
    	parameterIds = list; //参数解析并未完全解析完毕
    }
}
