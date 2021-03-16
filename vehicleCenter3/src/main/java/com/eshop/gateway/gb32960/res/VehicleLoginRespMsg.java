package com.eshop.gateway.gb32960.res;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.eshop.gateway.gb32960.config.gb32960Const;
import com.eshop.gateway.gb32960.pojo.GB32960DataPacket;
import com.eshop.gateway.gb32960.pojo.req.VehicleLoginMsg;

import io.netty.buffer.ByteBuf;

public class VehicleLoginRespMsg extends GB32960DataPacket {
    
	private LocalDateTime responseTime;//响应时间
	private Integer flowId;   //流水号    
    private  String iccid;//ICCID    
    private Short count;//可充电储能子系统数   
    private Short length;//可充电子系统编码长度  
    private List<String> codes; //可充电储能系统编码集合,长度等于count x length
    
    public void setResponseTime(LocalDateTime responseTime) {
    	this.responseTime=responseTime;
    }
	
    public LocalDateTime getResponseTime() {
    	return responseTime;
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
    
    @Override
    public ByteBuf toByteBufMsg() {
        ByteBuf bb = super.toByteBufMsg();
		  //设置报文响应时间 
          bb.writeByte(responseTime.getYear()-2000);
		  bb.writeByte(responseTime.getMonthValue());
		  bb.writeByte(responseTime.getDayOfMonth());
		  bb.writeByte(responseTime.getHour()); 
		  bb.writeByte(responseTime.getMinute());
		  bb.writeByte(responseTime.getSecond());
          bb.writeShort(flowId);//流水号
          bb.writeBytes(iccid.getBytes(gb32960Const.ASCII_CHARSET));//iccid
          bb.writeByte(count);
          bb.writeByte(length);
          if(codes != null && codes.size()>0) {
        	  for(String item : codes) {
            	  bb.writeBytes(item.getBytes(gb32960Const.ASCII_CHARSET));
          	}
          }
          return bb;
    }
    
    public static VehicleLoginRespMsg success(VehicleLoginMsg msg) {
    	VehicleLoginRespMsg resp = new VehicleLoginRespMsg();
    	resp.getHeader().setRequestType(msg.getHeader().getRequestType());
        resp.getHeader().setResponseTag(gb32960Const.RESPONSE_SUCCESS);//设置应答标志
        resp.getHeader().setVin(msg.getHeader().getVin()); //设置唯一识别码
        resp.getHeader().setEncrypTionType(gb32960Const.ENCRYPTION_NO); //不加密
        resp.getHeader().setPayloadLength(msg.getHeader().getPayloadLength()); //设置数据长度
        //resp.getHeader().setPayloadLength(8); //设置数据长度
        LocalDateTime now = LocalDateTime.now();//获取当前时间
        resp.setResponseTime(now);//设置报文应答时间
        resp.setFlowId(msg.getFlowId());//设置流水号
        resp.setIccid(msg.getIccid()); //设置iccid
        resp.setCount(msg.getCount()); //可充电储能系统子系统数量
        resp.setLength(msg.getLength());//子系统编码长度
        resp.setCodes(msg.getCodes());//可充电储能系统编码
        return resp;
    }
    
    
}
