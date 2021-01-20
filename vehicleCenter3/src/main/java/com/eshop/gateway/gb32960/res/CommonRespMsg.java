package com.eshop.gateway.gb32960.res;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import com.eshop.gateway.gb32960.config.gb32960Const;
import com.eshop.gateway.gb32960.pojo.GB32960DataPacket;

import io.netty.buffer.ByteBuf;

public class CommonRespMsg extends GB32960DataPacket{
	
    
    private LocalDateTime responseTime;//报文响应时间
    
    public void setResponseTime(LocalDateTime responseTime) {
    	this.responseTime=responseTime;
    }
	
    public LocalDateTime getResponseTime() {
    	return responseTime;
    }
    
    
    @Override
    public ByteBuf toByteBufMsg() {
        ByteBuf bb = super.toByteBufMsg();
		/*
		 * //设置报文响应时间 bb.writeByte(responseTime.getYear()-2000);
		 * bb.writeByte(responseTime.getMonthValue());
		 * bb.writeByte(responseTime.getDayOfMonth());
		 * bb.writeByte(responseTime.getHour()); bb.writeByte(responseTime.getMinute());
		 * bb.writeByte(responseTime.getSecond());
		 */
        bb.writeByte(0x00);//数据单元为0x00
        return bb;
    }

	public static CommonRespMsg success(GB32960DataPacket msg, Integer flowId) {
    	CommonRespMsg resp = new CommonRespMsg();
    	resp.getHeader().setRequestType(msg.getHeader().getRequestType());
        resp.getHeader().setResponseTag(gb32960Const.RESPONSE_SUCCESS);//设置应答标志
        resp.getHeader().setVin(msg.getHeader().getVin()); //设置唯一识别码
        resp.getHeader().setEncrypTionType(gb32960Const.ENCRYPTION_NO); //不加密
        resp.getHeader().setPayloadLength(1); //设置数据长度
        //resp.setResponseTime();//设置报文请求时间
        return resp;
    }
    
    public static CommonRespMsg failure(GB32960DataPacket msg, Integer flowId) {
    	CommonRespMsg resp = new CommonRespMsg();
    	resp.getHeader().setRequestType(msg.getHeader().getRequestType());
        resp.getHeader().setResponseTag(gb32960Const.RESPONSE_ERROR);//设置应答标志
        resp.getHeader().setVin(msg.getHeader().getVin()); //设置唯一识别码
        resp.getHeader().setEncrypTionType(gb32960Const.ENCRYPTION_NO); //不加密
        resp.getHeader().setPayloadLength(1); //设置数据长度
        //resp.setResponseTime();//设置报文请求时间
        return resp;
    }
    
    public static CommonRespMsg vinRepeat(GB32960DataPacket msg, Integer flowId) {
    	CommonRespMsg resp = new CommonRespMsg();
    	resp.getHeader().setRequestType(msg.getHeader().getRequestType());
        resp.getHeader().setResponseTag(gb32960Const.RESPONSE_VIN_DUPLICATE);//设置应答标志
        resp.getHeader().setVin(msg.getHeader().getVin()); //设置唯一识别码
        resp.getHeader().setEncrypTionType(gb32960Const.ENCRYPTION_NO); //不加密
        resp.getHeader().setPayloadLength(1); //设置数据长度
        //resp.setResponseTime();//设置报文请求时间
        return resp;
    }

}
