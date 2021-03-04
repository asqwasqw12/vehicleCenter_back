package com.eshop.gateway.gb32960.pojo.req;


import com.eshop.gateway.gb32960.config.gb32960Const;
import com.eshop.gateway.gb32960.pojo.GB32960DataPacket;

import io.netty.buffer.ByteBuf;

public class CRRCLoginMsg extends GB32960DataPacket{
	

	private String terminalNum;   //终端编号    
    private  String iccid;//ICCID    
    
    public void setTerminalNum(String terminalNum) {
    	this.terminalNum = terminalNum;
    }
    
    public String getTerminalNum() {
    	return terminalNum;
    }
    

    
    public void setIccid(String iccid) {
    	this.iccid = iccid;
    }
    
    public String getIccid() {
    	return iccid;
    }
    

    public CRRCLoginMsg(ByteBuf byteBuf) {
    	super(byteBuf);
    }
    
    @Override
    public void parseBody() {
    	this.terminalNum = this.payload.readCharSequence(6, gb32960Const.ASCII_CHARSET).toString();
    	this.iccid = this.payload.readCharSequence(20, gb32960Const.ASCII_CHARSET).toString();
    }

}
