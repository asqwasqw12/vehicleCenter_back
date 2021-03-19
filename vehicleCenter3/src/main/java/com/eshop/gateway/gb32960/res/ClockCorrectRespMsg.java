package com.eshop.gateway.gb32960.res;

import java.time.LocalDateTime;

import com.eshop.gateway.gb32960.config.gb32960Const;
import com.eshop.gateway.gb32960.pojo.GB32960DataPacket;

import io.netty.buffer.ByteBuf;

public class ClockCorrectRespMsg extends GB32960DataPacket{

    private LocalDateTime clockCorrectTime ; //校对时间
    
    public void setClockCorrectTime(LocalDateTime clockCorrectTime) {
    	this.clockCorrectTime=clockCorrectTime;
    }
	
    public LocalDateTime getClockCorrectTime() {
    	return clockCorrectTime;
    }
    
    
    @Override
    public ByteBuf toByteBufMsg() {
        ByteBuf bb = super.toByteBufMsg();
          //设置报文响应时间 
          bb.writeByte(clockCorrectTime.getYear()-2000);
		  bb.writeByte(clockCorrectTime.getMonthValue());
		  bb.writeByte(clockCorrectTime.getDayOfMonth());
		  bb.writeByte(clockCorrectTime.getHour()); 
		  bb.writeByte(clockCorrectTime.getMinute());
		  bb.writeByte(clockCorrectTime.getSecond());
        return bb;
    }

    public static ClockCorrectRespMsg success(GB32960DataPacket msg) {
    	ClockCorrectRespMsg resp = new ClockCorrectRespMsg();
    	resp.getHeader().setRequestType(msg.getHeader().getRequestType());
        resp.getHeader().setResponseTag(gb32960Const.RESPONSE_SUCCESS);//设置应答标志
        resp.getHeader().setVin(msg.getHeader().getVin()); //设置唯一识别码
        resp.getHeader().setEncrypTionType(gb32960Const.ENCRYPTION_NO); //不加密
        resp.setClockCorrectTime(LocalDateTime.now());
        resp.getHeader().setPayloadLength(6); //设置数据长度
        return resp;
    }
    
    @Override
    public String toString() {
    	return "clockCorrectTime="+clockCorrectTime.toString();
    }
}
