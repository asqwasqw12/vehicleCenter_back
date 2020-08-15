package com.eshop.modules.websocket;

public class SocketMsg {
	
	private String msg;
	private MsgType msgType;
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public MsgType getMsgType() {
		return msgType;
	}
	
	public void setMsg(MsgType msgType) {
		this.msgType = msgType;
	}

	public SocketMsg(String msg, MsgType msgType) {
		this.msg = msg;
		this.msgType = msgType;
	}

}
