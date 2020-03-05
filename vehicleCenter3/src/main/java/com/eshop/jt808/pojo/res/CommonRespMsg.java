package com.eshop.jt808.pojo.res;

import com.eshop.jt808.config.JT808Const;
import com.eshop.jt808.pojo.DataPacket;

import io.netty.buffer.ByteBuf;


//@Description:通用响应包
public class CommonRespMsg extends DataPacket {

	public static final byte SUCCESS = 0;//成功/确认
    public static final byte FAILURE = 1;//失败
    public static final byte MSG_ERROR = 2;//消息有误
    public static final byte UNSUPPORTED = 3;//不支持
    public static final byte ALARM_PROCESS_ACK = 4;//报警处理确认

    private short replyFlowId; //应答流水号 2字节
    private short replyId; //应答 ID  2字节
    private byte result;    //结果 1字节
    
    public void setReplyFlowId(short replyFlowId ) {
    	this.replyFlowId = replyFlowId;
    }
    
    public short getReplyFlowId() {
    	return this.replyFlowId;
    }
    
    public void setReplyId(short replyId) {
    	this.replyId = replyId;
    }
    
    public short getReplyId() {
    	return this.replyId;
    }
    
    public void setResult(byte result) {
    	this.result = result;
    }
    
    public byte getResult() {
    	return this.result;
    }
    
    public CommonRespMsg() {
        this.header.setMsgId(JT808Const.SERVER_RESP_COMMON);
    }
    
    @Override
    public ByteBuf toByteBufMsg() {
        ByteBuf bb = super.toByteBufMsg();
        bb.writeShort(replyFlowId);
        bb.writeShort(replyId);
        bb.writeByte(result);
        return bb;
    }
    
    public static CommonRespMsg success(DataPacket msg, short flowId) {
        CommonRespMsg resp = new CommonRespMsg();
        resp.getHeader().setTerminalPhone(msg.getHeader().getTerminalPhone());
        resp.getHeader().setFlowId(flowId);
        resp.setReplyFlowId(msg.getHeader().getFlowId());
        resp.setReplyId(msg.getHeader().getMsgId());
        resp.setResult(SUCCESS);
        return resp;
}
}
