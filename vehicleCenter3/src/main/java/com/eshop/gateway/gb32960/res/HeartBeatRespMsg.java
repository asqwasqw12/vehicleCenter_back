package com.eshop.gateway.gb32960.res;


import com.eshop.gateway.gb32960.config.gb32960Const;
import com.eshop.gateway.gb32960.pojo.GB32960DataPacket;

import io.netty.buffer.ByteBuf;

public class HeartBeatRespMsg extends GB32960DataPacket{
	
    
    @Override
    public ByteBuf toByteBufMsg() {
        ByteBuf bb = super.toByteBufMsg();
        bb.writeByte(0x00);//返回数据0x00
        return bb;
    }

    public static HeartBeatRespMsg success(GB32960DataPacket msg) {
    	HeartBeatRespMsg resp = new HeartBeatRespMsg();
    	resp.getHeader().setRequestType(msg.getHeader().getRequestType());
        resp.getHeader().setResponseTag(gb32960Const.RESPONSE_SUCCESS);//设置应答标志
        resp.getHeader().setVin(msg.getHeader().getVin()); //设置唯一识别码
        resp.getHeader().setEncrypTionType(gb32960Const.ENCRYPTION_NO); //不加密
        resp.getHeader().setPayloadLength(1); //设置数据长度
        return resp;
    }
}
