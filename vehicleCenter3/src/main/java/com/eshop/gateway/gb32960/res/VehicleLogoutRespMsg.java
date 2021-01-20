package com.eshop.gateway.gb32960.res;

import java.time.LocalDateTime;

import com.eshop.gateway.gb32960.config.gb32960Const;
import com.eshop.gateway.gb32960.pojo.GB32960DataPacket;

import io.netty.buffer.ByteBuf;

public class VehicleLogoutRespMsg extends GB32960DataPacket{

    
    private LocalDateTime responseTime;//报文响应时间
    
    private Integer flowId; //流水号
    
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
          return bb;
    }
    
    public static VehicleLogoutRespMsg success(GB32960DataPacket msg, Integer flowId) {
    	VehicleLogoutRespMsg resp = new VehicleLogoutRespMsg();
    	resp.getHeader().setRequestType(msg.getHeader().getRequestType());
        resp.getHeader().setResponseTag(gb32960Const.RESPONSE_SUCCESS);//设置应答标志
        resp.getHeader().setVin(msg.getHeader().getVin()); //设置唯一识别码
        resp.getHeader().setEncrypTionType(gb32960Const.ENCRYPTION_NO); //不加密
        resp.getHeader().setPayloadLength(8); //设置数据长度
        LocalDateTime now = LocalDateTime.now();//获取当前时间
        resp.setResponseTime(now);//设置报文应答时间
        resp.setFlowId(flowId);
        return resp;
    }
}
