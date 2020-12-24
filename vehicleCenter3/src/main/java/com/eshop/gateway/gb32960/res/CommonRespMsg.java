package com.eshop.gateway.gb32960.res;

import java.time.ZonedDateTime;

import com.eshop.gateway.gb32960.pojo.DataPacket;

import io.netty.buffer.ByteBuf;

public class CommonRespMsg extends DataPacket{
	
	public static final short SUCCESS = 01;//成功/确认
    public static final short FAILURE = 02;//错误
    public static final short VIN_REPEAT =03;//VIN重复
    public static final short COMMAND = 0xFE;//不支持
    
    public static final short ENCRYPTION_NO = 01;//不加密
    public static final short ENCRYPTION_RSA = 02;//RSA加密
    public static final short ENCRYPTION_AES128 = 03;//不加密
    public static final short ENCRYPTION_EXCEPTION = 0xFE;//异常
    public static final short ENCRYPTION_INVALID = 0xFE;//无效
    
    private ZonedDateTime requestTime;//报文请求时间
    
    public void setRequestTime(ZonedDateTime requestTime) {
    	this.requestTime=requestTime;
    }
	
    public ZonedDateTime getRequestTime() {
    	return requestTime;
    }
    
    
    @Override
    public ByteBuf toByteBufMsg() {
        ByteBuf bb = super.toByteBufMsg();
        bb.writeByte(requestTime.getYear()-2000);
        bb.writeByte(requestTime.getMonthValue());
        bb.writeByte(requestTime.getDayOfMonth());
        bb.writeByte(requestTime.getHour());
        bb.writeByte(requestTime.getMinute());
        bb.writeByte(requestTime.getSecond());
        return bb;
    }

	public static CommonRespMsg success(DataPacket msg, Integer flowId,ZonedDateTime requestTime) {
    	CommonRespMsg resp = new CommonRespMsg();
    	resp.getHeader().setRequestType(msg.getHeader().getRequestType());
        resp.getHeader().setResponseTag(SUCCESS);//设置应答标志
        resp.getHeader().setVin(msg.getHeader().getVin()); //设置唯一识别码
        resp.getHeader().setEncrypTionType(ENCRYPTION_NO); //不加密
        resp.getHeader().setPayloadLength(6); //设置数据长度
        resp.setRequestTime(requestTime);//设置报文请求时间
        return resp;
    }
    
    public static CommonRespMsg failure(DataPacket msg, Integer flowId,ZonedDateTime requestTime) {
    	CommonRespMsg resp = new CommonRespMsg();
    	resp.getHeader().setRequestType(msg.getHeader().getRequestType());
        resp.getHeader().setResponseTag(FAILURE);//设置应答标志
        resp.getHeader().setVin(msg.getHeader().getVin()); //设置唯一识别码
        resp.getHeader().setEncrypTionType(ENCRYPTION_NO); //不加密
        resp.getHeader().setPayloadLength(6); //设置数据长度
        resp.setRequestTime(requestTime);//设置报文请求时间
        return resp;
    }
    
    public static CommonRespMsg vinRepeat(DataPacket msg, Integer flowId,ZonedDateTime requestTime) {
    	CommonRespMsg resp = new CommonRespMsg();
    	resp.getHeader().setRequestType(msg.getHeader().getRequestType());
        resp.getHeader().setResponseTag(VIN_REPEAT);//设置应答标志
        resp.getHeader().setVin(msg.getHeader().getVin()); //设置唯一识别码
        resp.getHeader().setEncrypTionType(ENCRYPTION_NO); //不加密
        resp.getHeader().setPayloadLength(6); //设置数据长度
        resp.setRequestTime(requestTime);//设置报文请求时间
        return resp;
    }

}
