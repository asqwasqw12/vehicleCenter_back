package com.eshop.jt808.pojo.req;

import com.eshop.jt808.pojo.DataPacket;

import io.netty.buffer.ByteBuf;

//@Description:注销包 没有包体 空包
public class CancellationMsg extends DataPacket {

	public CancellationMsg(ByteBuf byteBuf) {
		super(byteBuf);
	}
}
