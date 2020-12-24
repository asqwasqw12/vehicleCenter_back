package com.eshop.gateway.gb32960.pojo.req;


import com.eshop.gateway.gb32960.pojo.DataPacket;

import io.netty.buffer.ByteBuf;

public class HeartBeatMsg extends DataPacket{
	
    public HeartBeatMsg(ByteBuf byteBuf) {
    	super(byteBuf);
    }
    
    @Override
    public void parseBody() {
    	//do nothing
    }
}