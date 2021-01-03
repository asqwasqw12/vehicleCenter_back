package com.eshop.gateway.gb32960.res;

import java.time.ZonedDateTime;

import com.eshop.gateway.gb32960.pojo.GB32960DataPacket;

import io.netty.buffer.ByteBuf;

public class HeartBeatRespMsg extends GB32960DataPacket{
	
	public static final short SUCCESS = 01;//成功/确认
    public static final short FAILURE = 02;//错误
    public static final short VIN_REPEAT =03;//VIN重复
    public static final short COMMAND = 0xFE;//不支持
    
    public static final short ENCRYPTION_NO = 01;//不加密
    public static final short ENCRYPTION_RSA = 02;//RSA加密
    public static final short ENCRYPTION_AES128 = 03;//不加密
    public static final short ENCRYPTION_EXCEPTION = 0xFE;//异常
    public static final short ENCRYPTION_INVALID = 0xFE;//无效
    
    @Override
    public ByteBuf toByteBufMsg() {
        ByteBuf bb = super.toByteBufMsg();
        return bb;
    }

    public static HeartBeatRespMsg success(GB32960DataPacket msg) {
    	HeartBeatRespMsg resp = new HeartBeatRespMsg();
    	resp.getHeader().setRequestType(msg.getHeader().getRequestType());
        resp.getHeader().setResponseTag(SUCCESS);//设置应答标志
        resp.getHeader().setVin(msg.getHeader().getVin()); //设置唯一识别码
        resp.getHeader().setEncrypTionType(ENCRYPTION_NO); //不加密
        resp.getHeader().setPayloadLength(0); //设置数据长度
        return resp;
    }
}
