package com.eshop.gateway.gb32960.pojo;

public class DataHeader {
	public static final int HEADER_LENGTH = 22;
	
	private Short requestType; //命令标识
	private Short responseTag; //应答标志
	private String vin;  //车辆唯一识别码
	private Short encrypTionType; //加密方式，0x01:数据不加密，0x02:数据经过RSA算法加密；0x03:数据经过AES128位算法加密；0XFE:表示异常，0xFF:表示无效
	private Integer payloadLength; //数据单元长度
	
	public void setRequestType(Short requestType) {
		this.requestType = requestType;
	}
	
	public Short getRequestType() {
		return requestType;
	}
	
	public void setResponseTag(Short responseTag) {
		this.responseTag = responseTag;
	}

	public Short getResponseTag() {
		return responseTag;
	}
	
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	public String getVin() {
		return vin;
	}
	
	public void  setEncrypTionType(Short encrypTionType) {
		this.encrypTionType = encrypTionType;		
	}
	
	public Short getEncrypTionType() {
		return encrypTionType;
	}
	
	public void setPayloadLength(Integer payloadLength) {
		this.payloadLength = payloadLength;
	}
	
	public Integer getPayloadLength() {
		return payloadLength;
	}
}
