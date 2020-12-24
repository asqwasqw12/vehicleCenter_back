package com.eshop.gateway.gb32960.res;

import com.eshop.gateway.gb32960.pojo.DataPacket;

public class PlatformLoginRespMsg extends DataPacket {
	
	public static final short SUCCESS = 01;//成功/确认
    public static final short FAILURE = 02;//错误
    public static final short MSG_ERROR =03;//VIN重复
    public static final short COMMAND = 0xFE;//不支持
    
    public static final short ENCRYPTION_NO = 01;//不加密
    public static final short ENCRYPTION_RSA = 02;//RSA加密
    public static final short ENCRYPTION_AES128 = 03;//不加密
    public static final short ENCRYPTION_EXCEPTION = 0xFE;//异常
    public static final short ENCRYPTION_INVALID = 0xFE;//异常
    
    public PlatformLoginRespMsg() {
        this.header.setResponseTag((short) 0x05);//设置命令标识
    }

    public static PlatformLoginRespMsg success(DataPacket msg, Integer flowId) {
    	PlatformLoginRespMsg resp = new PlatformLoginRespMsg();
        resp.getHeader().setResponseTag(SUCCESS);//设置应答标志
        resp.getHeader().setVin(msg.getHeader().getVin()); //设置唯一识别码
        resp.getHeader().setEncrypTionType(ENCRYPTION_NO); //不加密
        resp.getHeader().setPayloadLength(0); //设置数据长度
        return resp;
    }
    
    public static PlatformLoginRespMsg failure(DataPacket msg, Integer flowId) {
    	PlatformLoginRespMsg resp = new PlatformLoginRespMsg();
        resp.getHeader().setResponseTag(FAILURE);//设置应答标志
        resp.getHeader().setVin(msg.getHeader().getVin()); //设置唯一识别码
        resp.getHeader().setEncrypTionType(ENCRYPTION_NO); //不加密
        resp.getHeader().setPayloadLength(0); //设置数据长度
        return resp;
    }
}
