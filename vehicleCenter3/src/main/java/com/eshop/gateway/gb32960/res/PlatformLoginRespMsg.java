package com.eshop.gateway.gb32960.res;

import com.eshop.gateway.gb32960.config.gb32960Const;
import com.eshop.gateway.gb32960.pojo.GB32960DataPacket;

public class PlatformLoginRespMsg extends GB32960DataPacket {
	
    
    public PlatformLoginRespMsg() {
        this.header.setResponseTag((short) 0x05);//设置命令标识
    }

    public static PlatformLoginRespMsg success(GB32960DataPacket msg, Integer flowId) {
    	PlatformLoginRespMsg resp = new PlatformLoginRespMsg();
        resp.getHeader().setResponseTag(gb32960Const.RESPONSE_SUCCESS);//设置应答标志
        resp.getHeader().setVin(msg.getHeader().getVin()); //设置唯一识别码
        resp.getHeader().setEncrypTionType(gb32960Const.ENCRYPTION_NO); //不加密
        resp.getHeader().setPayloadLength(0); //设置数据长度
        return resp;
    }
    
    public static PlatformLoginRespMsg failure(GB32960DataPacket msg, Integer flowId) {
    	PlatformLoginRespMsg resp = new PlatformLoginRespMsg();
        resp.getHeader().setResponseTag(gb32960Const.RESPONSE_ERROR);//设置应答标志
        resp.getHeader().setVin(msg.getHeader().getVin()); //设置唯一识别码
        resp.getHeader().setEncrypTionType(gb32960Const.ENCRYPTION_NO); //不加密
        resp.getHeader().setPayloadLength(0); //设置数据长度
        return resp;
    }
}
