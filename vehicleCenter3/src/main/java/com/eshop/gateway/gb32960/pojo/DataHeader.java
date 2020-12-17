package com.eshop.gateway.gb32960.pojo;

public class DataHeader {
	public static final int HEADER_LENGTH = 22;
	
	private byte requestType; //命令标识
	private byte responseTag; //应答标志
	private String vin;  //车辆唯一识别码
	private byte encrypTionType; //加密方式，0x01:数据不加密，0x02:数据经过RSA算法加密；0x03:数据经过AES128位算法加密；0XFE:表示异常，0xFF:表示无效
	private short payloadLength; //数据单元长度
	
	public void setRequestType(byte requestType) {
		this.requestType = requestType;
	}
	
	public byte getRequestType() {
		return requestType;
	}
	
	public void setResponseTag(byte responseTag) {
		this.responseTag = responseTag;
	}

	public byte getResponseTag() {
		return responseTag;
	}
	
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	public String getVin() {
		return vin;
	}
	
	public void  setEncrypTionType(byte encrypTionType) {
		this.encrypTionType = encrypTionType;		
	}
	
	public byte getEncrypTionType() {
		return encrypTionType;
	}
	
	public void setPayloadLength(short payloadLength) {
		this.payloadLength = payloadLength;
	}
	
	public short getPayloadLength() {
		return payloadLength;
	}
}
