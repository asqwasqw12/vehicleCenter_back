package com.eshop.jt808.pojo.req;

import com.eshop.jt808.pojo.DataPacket;
import io.netty.buffer.ByteBuf;

// @Description:心跳包 没有包体 空包
public class HeartBeatMsg extends DataPacket {
	
	public HeartBeatMsg(ByteBuf byteBuf) {
        super(byteBuf);
    }

}
