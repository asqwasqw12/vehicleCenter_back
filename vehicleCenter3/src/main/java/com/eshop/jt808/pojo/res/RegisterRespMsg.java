package com.eshop.jt808.pojo.res;

import org.apache.commons.lang3.StringUtils;

import com.eshop.jt808.config.JT808Const;
import com.eshop.jt808.pojo.DataPacket;

import io.netty.buffer.ByteBuf;


//@Description:注册响应包
public class RegisterRespMsg extends DataPacket{
	
	public static final byte SUCCESS = 0;//成功
    public static final byte VEHICLE_ALREADY_REGISTER = 1;//车辆已被注册
    public static final byte NOT_IN_DB = 2;//数据库无该车辆
    public static final byte TERMINAL_ALREADY_REGISTER = 3;//终端已被注册

    private short replyFlowId; //应答流水号 2字节
    private byte result;    //结果 1字节
    private String authCode; //鉴权码
    
    public void setReplyFlowId(short replyFlowId ) {
    	this.replyFlowId = replyFlowId;
    }
    
    public short getReplyFlowId() {
    	return this.replyFlowId;
    }

    
    public void setResult(byte result) {
    	this.result = result;
    }
    
    public byte getResult() {
    	return this.result;
    }
    
    public void setAuthCode(String authCode ) {
    	this.authCode = authCode;
    }
    
    public String getAuthCode() {
    	return this.authCode;
    }

    public RegisterRespMsg() {
        this.getHeader().setMsgId(JT808Const.SERVER_RESP_REGISTER);
    }
    
    @Override
    public ByteBuf toByteBufMsg() {
        ByteBuf bb = super.toByteBufMsg();
        bb.writeShort(replyFlowId);
        bb.writeByte(result);
        if (result == SUCCESS && StringUtils.isNotBlank(authCode)) {//成功才写入鉴权码
            bb.writeBytes(authCode.getBytes(JT808Const.DEFAULT_CHARSET));
        }
        return bb;
    }
    
    public static RegisterRespMsg success(DataPacket msg, short flowId) {
        RegisterRespMsg resp = new RegisterRespMsg();
        resp.getHeader().setTerminalPhone(msg.getHeader().getTerminalPhone());
        resp.getHeader().setFlowId(flowId);
        resp.setReplyFlowId(msg.getHeader().getFlowId());
        resp.setResult(SUCCESS);
        resp.setAuthCode("SUCCESS");
        return resp;
    }
}
