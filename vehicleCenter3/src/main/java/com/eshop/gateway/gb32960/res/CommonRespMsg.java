package com.eshop.gateway.gb32960.res;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import com.eshop.gateway.gb32960.pojo.GB32960DataPacket;

import io.netty.buffer.ByteBuf;

public class CommonRespMsg extends GB32960DataPacket{
	
	public static final short SUCCESS = 01;//成功/确认
    public static final short FAILURE = 02;//错误
    public static final short VIN_REPEAT =03;//VIN重复
    public static final short COMMAND = 0xFE;//不支持
    
    public static final short ENCRYPTION_NO = 01;//不加密
    public static final short ENCRYPTION_RSA = 02;//RSA加密
    public static final short ENCRYPTION_AES128 = 03;//不加密
    public static final short ENCRYPTION_EXCEPTION = 0xFE;//异常
    public static final short ENCRYPTION_INVALID = 0xFE;//无效
    
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
        resp.getHeader().setResponseTag(SUCCESS);//设置应答标志
        resp.getHeader().setVin(msg.getHeader().getVin()); //设置唯一识别码
        resp.getHeader().setEncrypTionType(ENCRYPTION_NO); //不加密
        resp.getHeader().setPayloadLength(1); //设置数据长度
        //resp.setResponseTime();//设置报文请求时间
        return resp;
    }
    
    public static CommonRespMsg failure(GB32960DataPacket msg, Integer flowId) {
    	CommonRespMsg resp = new CommonRespMsg();
    	resp.getHeader().setRequestType(msg.getHeader().getRequestType());
        resp.getHeader().setResponseTag(FAILURE);//设置应答标志
        resp.getHeader().setVin(msg.getHeader().getVin()); //设置唯一识别码
        resp.getHeader().setEncrypTionType(ENCRYPTION_NO); //不加密
        resp.getHeader().setPayloadLength(1); //设置数据长度
        //resp.setResponseTime();//设置报文请求时间
        return resp;
    }
    
    public static CommonRespMsg vinRepeat(GB32960DataPacket msg, Integer flowId) {
    	CommonRespMsg resp = new CommonRespMsg();
    	resp.getHeader().setRequestType(msg.getHeader().getRequestType());
        resp.getHeader().setResponseTag(VIN_REPEAT);//设置应答标志
        resp.getHeader().setVin(msg.getHeader().getVin()); //设置唯一识别码
        resp.getHeader().setEncrypTionType(ENCRYPTION_NO); //不加密
        resp.getHeader().setPayloadLength(1); //设置数据长度
        //resp.setResponseTime();//设置报文请求时间
        return resp;
    }

}
