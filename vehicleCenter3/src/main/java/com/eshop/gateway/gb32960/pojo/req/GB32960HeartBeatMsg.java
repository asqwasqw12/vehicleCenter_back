package com.eshop.gateway.gb32960.pojo.req;

import com.eshop.gateway.gb32960.pojo.GB32960DataPacket;

import io.netty.buffer.ByteBuf;

public class GB32960HeartBeatMsg extends GB32960DataPacket{
	
    public GB32960HeartBeatMsg(ByteBuf byteBuf) {
    	super(byteBuf);
    }
    
    @Override
    public void parseBody() {
    	//do nothing
    }
}
