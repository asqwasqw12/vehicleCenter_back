package com.eshop.pojo;


public class Vehicle extends BasePojo {
	private String name;	//名称
	private String type;	//型号	
	private String vin;		//车架识别码
	private String license;	//牌照
	private String brand;	//品牌
	private String companyNum;	//出厂编号
	private String chassis;	//底盘品牌
	private String configure;		//配置
	private Long ownerCompanyId; //所属机构
	private Long userCompanyId;  //使用机构
    private String status;		//资产状态
    private String purchaseWay;	//购买方式
    private Byte  manufactureStatus;//制造状态，0-在制，1-入库，2-出库
    //非数据库
    private String ownerCompanyName;//所属机构名称
    private String userCompanyName;	//使用机构名称
    
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
    
    
    public void setBrand(String brand) {
    	this.brand = brand;
    }
    
    public String getBrand() {
    	return brand;
    }
    
    public void setCompanyNum(String companyNum) {
    	this.companyNum = companyNum;
    }
    
    public String getCompanyNum() {
    	return companyNum;
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
    
    public void setOwnerCompanyId(Long ownerCompanyId) {
    	this.ownerCompanyId = ownerCompanyId;
    }
    public Long getOwnerCompanyId() {
    	return ownerCompanyId;
    }
    
    public void setUserCompanyId(Long userCompanyId) {
    	this.userCompanyId = userCompanyId;
    }
    public Long getUserCompanyId() {
    	return userCompanyId;
    }
    
    public void setStatus(String status) {
    	this.status = status;
    }
    
    public String getStatus() {
    	return status;
    }
    
    public void setPurchaseWay(String purchaseWay) {
    	this.purchaseWay = purchaseWay;
    }
    
    public String getPurchaseWay() {
    	return purchaseWay;
    }
    
    public void setOwnerCompanyName(String ownerCompanyName) {
    	this.ownerCompanyName = ownerCompanyName;
    }
    
    public String getOwnerCompanyName() {
    	return ownerCompanyName;
    }
    
    public void setUserCompanyName(String userCompanyName) {
    	this.userCompanyName = userCompanyName;
    }
    
    public String getUserCompanyName() {
    	return userCompanyName;
    }
    
    public void setManufactureStatus(Byte  manufactureStatus) {
    	this.manufactureStatus = manufactureStatus;
    }
    
    public Byte getManufactureStatus() {
    	return  manufactureStatus;
    }
}
