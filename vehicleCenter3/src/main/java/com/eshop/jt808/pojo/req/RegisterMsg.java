package com.eshop.jt808.pojo.req;

import com.eshop.jt808.pojo.DataPacket;

import io.netty.buffer.ByteBuf;

//@Description:终端注册包
public class RegisterMsg extends DataPacket {

	private short provinceId;		//省域ID 2字节
    private short cityId;			//市县ID 2字节
    private String manufacturerId;	//制造商ID 5字节
    private String terminalType;	//终端型号 8字节
    private String terminalId;		//终端ID 7字节
    private byte licensePlateColor;	//车牌颜色 1字节
    private String licensePlate;	//车牌号 剩余字节
    
    public void setProvinceId(short provinceId) {
    	this.provinceId = provinceId;
    }
    
    public short getProvinceId() {
    	return this.provinceId;
    }
    
    public void setCityId(short cityId) {
    	this.cityId = cityId;
    }
    
    public short getCityId() {
    	return this.cityId;
    }
    
    public void setManufacturerId(String manufacturerId) {
    	this.manufacturerId = manufacturerId;
    }
    
    public String getManufacturerId() {
    	return this.manufacturerId;
    }
    
    public void setTerminalType(String terminalType) {
    	this.terminalType = terminalType;
    }
    
    public String getTerminalType() {
    	return this.terminalType;
    }
    
    public void setTerminalId(String terminalId) {
    	this.terminalId = terminalId;
    }
    
    public String getTerminalId() {
    	return this.terminalId;
    }
    
    public void setLicensePlateColor(byte licensePlateColor) {
    	this.licensePlateColor = licensePlateColor;
    }
    
    public byte getLicensePlateColor() {
    	return this.licensePlateColor;
    }
    
    public void setLicensePlate(String licensePlate) {
    	this.licensePlate = licensePlate;
    }
    
    public String getLicensePlate() {
    	return this.licensePlate;
    }
    
    public RegisterMsg(ByteBuf byteBuf) {
        super(byteBuf);
    }
    
    @Override
    public void parseBody() {
        ByteBuf bb = this.payload;
        this.setProvinceId(bb.readShort());
        this.setCityId(bb.readShort());
        this.setManufacturerId(readString(5));
        this.setTerminalType(readString(8));
        this.setTerminalId(readString(7));
        this.setLicensePlateColor(bb.readByte());
        this.setLicensePlate(readString(bb.readableBytes()));
    }
    
}
