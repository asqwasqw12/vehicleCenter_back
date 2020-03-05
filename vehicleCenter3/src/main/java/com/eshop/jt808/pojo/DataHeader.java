package com.eshop.jt808.pojo;

public class DataHeader {
	private short msgId;// 消息ID 2字节
    private short msgBodyProps;//消息体属性 2字节
    private String terminalPhone; // 终端手机号 6字节
    private short flowId;// 流水号 2字节
    
    public short getMsgId() {
    	return this.msgId;
    }
    
    public void setMsgId(short msgId) {
    	this.msgId = msgId;
    }
    
    public short getMsgBodyProps() {
    	return this.msgBodyProps;
    }
    
    public void setMsgBodyProps(short msgBodyProps) {
    	this.msgBodyProps = msgBodyProps;
    }
    
    public String getTerminalPhone() {
    	return this.terminalPhone;
    }
    
    public void setTerminalPhone(String terminalPhone) {
    	this.terminalPhone = terminalPhone;
    }
    
    public short getFlowId() {
    	return this.flowId;
    }

    public void setFlowId(short flowId) {
    	this.flowId = flowId;
    }
    
    //获取包体长度
    public short getMsgBodyLength() {
        return (short) (msgBodyProps & 0x3ff);
    }

    //获取加密类型 3bits
    public byte getEncryptionType() {
        return (byte) ((msgBodyProps & 0x1c00) >> 10);
    }

    //是否分包
    public boolean hasSubPackage() {
        return ((msgBodyProps & 0x2000) >> 13) == 1;
    }
}
