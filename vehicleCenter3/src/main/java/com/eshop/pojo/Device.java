package com.eshop.pojo;

public class Device extends BasePojo {

	private String number; //设备序列号
	private String type;   //设备型号
	private String name;   //设备名称
	private String brand;  //设备品牌
	private String version; //软件版本
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
	return type;
	}

	public void setType(String type) {
	this.type = type;
	}

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}

	public String getBrand() {
	return brand;
	}

	public void setBrand(String brand) {
	this.brand = brand;
	}
	public String getVersion() {
	return version;
	}

	public void setVersion(String version) {
	this.version = version;
	}

}
