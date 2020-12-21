package com.eshop.gateway.gb32960.pojo.req;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.eshop.gateway.gb32960.config.gb32960Const;
import com.eshop.gateway.gb32960.pojo.DataPacket;

import io.netty.buffer.ByteBuf;

public class ControlMsg extends DataPacket {
	private ZonedDateTime sampleTime;//命令控制时间
	
	private Short orderId; //命令ID
	
	private List<Short> orderParameter; //命令参数
	
	 public void setSampleTime(ZonedDateTime sampleTime) {
	    	this.sampleTime=sampleTime;
	    }
		
	    public ZonedDateTime getSampleTime() {
	    	return sampleTime;
	    }
	
   
    
    public void setOrderId(Short orderId) {
    	this.orderId = orderId;
    }
    
    public Short getOrderId() {
    	return orderId;
    }
    
    public void setOrderParameter(List<Short> orderParameter) {
    	this.orderParameter = orderParameter;
    }
    
    public List<Short> getOrderParameter(){
    	return orderParameter;
    }
    
    public ControlMsg(ByteBuf byteBuf) {
    	super(byteBuf);
    }
    
    @Override
    public void parseBody() {
    	
    	sampleTime = ZonedDateTime.of((this.payload.readByte()+ 2000),this.payload.readByte(),this.payload.readByte(),
        		this.payload.readByte(),this.payload.readByte(),this.payload.readByte(),0,gb32960Const.ZONE_UTC8);
    	orderId = payload.readUnsignedByte();
    	List<Short> list= new ArrayList<Short>();
    	//远程升级命令数据解析
    	if(orderId == 0x01) {
    		//todo...
    	}
    	//报警/预警命令数据解析
    	if(orderId == 0x06) {
    		//todo...
    	}
    }
}
