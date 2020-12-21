package com.eshop.gateway.gb32960.pojo.req;

import com.eshop.gateway.gb32960.pojo.DataPacket;

import io.netty.buffer.ByteBuf;

public class ClockCorrectMsg extends DataPacket{
	
    public ClockCorrectMsg(ByteBuf byteBuf) {
    	super(byteBuf);
    }
    
    @Override
    public void parseBody() {
    	//do nothing
    }

}
