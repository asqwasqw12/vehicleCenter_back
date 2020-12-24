package com.eshop.pojo;

import java.util.List;

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
    
    //按照gb32960新增
    private String iccid; //车辆终端所使用SIM卡ICCID编号
    private Integer energyType;//0：纯电，1：插电混动，2：燃料电池，3：燃油，4：燃气
    private Integer maxSpeed;//最高车速
    private Integer maxRange;//续驶里程
    private String  transmissionRatio;//各档位传动比
    private Integer chargeDeviceCount;//可充电储能装置数量
    private Integer driveMotorCount;//驱动电机数量
    //如果是燃油车
    private String engineId;//发动机编号
    private String oilType;//燃油类型
    private String oilLabel;//燃油标号
    private Float maxPower;//最大输出功率
    private Float maxTorque;//最大扭矩
    //非数据库
    private List<ChargeDevice> chargeDevices;//可充电储能装置列表
    private List<DriveMotor> driveMotors;//驱动电机列表
    
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
    
    public void setIccid(String iccid) {
    	this.iccid = iccid;
    }
    
    public String getIccid() {
    	return iccid;
    }
    
    public void setEnergyType(Integer eneryType) {
    	this.energyType = eneryType;
    }
    
    public Integer getEnergyType() {
    	return energyType;
    }
    
    public void setMaxSpeed(Integer maxSpeed) {
    	this.maxSpeed = maxSpeed;
    }
    
    public Integer getMaxSpeed() {
    	return maxSpeed;
    }
    
    public void setMaxRange(Integer maxRange) {
    	this.maxRange = maxRange;
    }
    
    public Integer getMaxRange() {
    	return maxRange;
    }
    
    public void setTransmissionRatio(String transmissionRatio) {
    	this.transmissionRatio = transmissionRatio;
    }
    
    public String getTransmissionRatio() {
    	return transmissionRatio;
    }
    
    public void setChargeDeviceCount(Integer chargeDeviceCount) {
    	this.chargeDeviceCount = chargeDeviceCount;
    }
    
    public Integer getChargeDeviceCount() {
    	return chargeDeviceCount;
    }
    
    public void setDriveMotorCount(Integer driveMotorCount) {
    	this.driveMotorCount = driveMotorCount;
    }
    
    public Integer getDriveMotorCount() {
    	return driveMotorCount;
    }
    
    public void setEngineId(String engineId) {
    	this.engineId = engineId;
    }
    
    public String getEngineId() {
    	return engineId;
    }
    
    public void setOilType(String oilType) {
    	this.oilType = oilType;
    }
    
    public String getOilType() {
    	return oilType;
    }
    
    public void setOilLabel(String oilLabel) {
    	this.oilLabel = oilLabel;
    }
    
    public String getOilLabel() {
    	return oilLabel;
    }
    
    public void setMaxPower(Float maxPower) {
    	this.maxPower = maxPower;
    }
    
    public Float getMaxPower() {
    	return maxPower;
    }
    
    public void setMaxTorque(Float maxTorque) {
    	this.maxTorque = maxTorque;
    }
    
    public Float getMaxTorque() {
    	return maxTorque;
    }
    
    public void setChargeDevices(List<ChargeDevice> chargeDevices) {
    	this.chargeDevices = chargeDevices;
    }
    
    public List<ChargeDevice> getChargeDevices() {
    	return chargeDevices;
    }
    
    public void setDriveMotors(List<DriveMotor> driveMotors) {
    	this.driveMotors = driveMotors;
    }
    
    public List<DriveMotor> getDriveMotors() {
    	return driveMotors;
    }
    
}
