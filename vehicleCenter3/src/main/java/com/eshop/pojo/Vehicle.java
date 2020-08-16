package com.eshop.pojo;


public class Vehicle extends BasePojo {
	private String name;	//名称
	private String type;	//型号	
	private String vin;		//车架识别码
	private String license;	//牌照
	private Long companyId;	//出厂编号
	private String chassis;	//底盘品牌
	private String configure;		//配置

    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getName() {
    	return name;
    }
    
    
    
    public void setType(String type) {
    	this.type = type;
    }
    
    public String getType() {
    	return type;
    }
    
    public void setVin(String vin) {
    	this.vin = vin;
    }
    
    public String getVin() {
    	return vin;
    }
    
    public void setLicense(String license) {
    	this.license = license;
    }
    
    public String getLicense() {
    	return license;
    }
    
    public void setCompanyId(Long companyId) {
    	this.companyId = companyId;
    }
    
    public Long getCompanyId() {
    	return companyId;
    }
     
    public void setChassis(String chassis) {
    	this.chassis = chassis;
    }
    
    public String getChassis() {
    	return chassis;
    }
    
    public void setConfigure(String configure) {
    	this.configure = configure;
    }
    
    public String getConfigure() {
    	return configure;
    }
    
}
