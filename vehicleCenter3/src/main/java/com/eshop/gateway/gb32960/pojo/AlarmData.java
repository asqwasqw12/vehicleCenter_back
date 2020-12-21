package com.eshop.gateway.gb32960.pojo;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class AlarmData {
	
	Long id; //
	
	private Long vehicleId;			//车辆Id
	
	private String terminalPhone; // 终端手机号
	
	private String vin; //车辆唯一识别码

	private Date createTime;	//创建时间
	
	//数据采集时间
	private ZonedDateTime sampleTime;//采样时间
	
	
	//报警等级,有效值范围：0-3,0：表示无故障，1：表示一级故障，2：表示2级故障，3：表示3级故障，为最高级别故障，OxFE:表示异常，0xFF：表示无效
    private Short level;

    //通用报警标志
    private Long alarmInfo;

    /**
     * 温度差异报警
     */
    private Boolean temperatureDifferential;

    /**
     * 电池高温报警
     */
    private Boolean batteryHighTemperature;

    /**
     * 车载储能装置类型过压报警
     */
    private Boolean deviceTypeOverVoltage;

    /**
     * 车载储能装置类型欠压报警
     */
    private Boolean deviceTypeUnderVoltage;

    /**
     * SOC过低报警
     */
    private Boolean socLow;

    /**
     * 单体电池过压报警
     */
    private Boolean monomerBatteryOverVoltage;

    /**
     * 单体电池欠压报警
     */
    private Boolean monomerBatteryUnderVoltage;

    /**
     * SOC过高报警
     */
    private Boolean socHigh;

    /**
     * SOC跳变报警
     */
    private Boolean socJump;

    /**
     * 车载储能装置类型不匹配报警
     */
    private Boolean deviceTypeDontMatch;

    /**
     * 单体电池一致性差报警
     */
    private Boolean batteryConsistencyPoor;

    /**
     * 绝缘报警
     */
    private Boolean insulation;

    /**
     * DC温度报警
     */
    private Boolean dcTemperature;

    /**
     * 制动系统报警
     */
    private Boolean brakingSystem;

    /**
     * DC状态报警
     */
    private Boolean dcStatus;

    /**
     * 驱动电机控制器温度报警
     */
    private Boolean driveMotorControllerTemperature;

    /**
     * 高压互锁报警
     */
    private Boolean highPressureInterlock;

    /**
     * 驱动电机温度报警
     */
    private Boolean driveMotorTemperature;

    /**
     * 车载储能装置过充报警
     */
    private Boolean deviceTypeOverFilling;

    //可储能装置故障总数
    private Short deviceFailureCount;

    //可储能装置故障信息列表
    private List<Long> deviceFailureCodes;

    //驱动电机故障总数
    private Short driveMotorFailureCount;

    //驱动电机故障信息列表
    private List<Long> driveMotorFailureCodes;

    //发动机故障总数
    private Short engineFailureCount;

    //发动机故障信息列表
    private List<Long> engineFailureCodes;

    //其他故障总数
    private Short otherFailure;

    //其他故障信息列表
    private List<Long> otherFailureCodes;
	
	public void setId(Long id) {
    	this.id = id;
    }
    
    public Long getId() {
    	return this.id;
    }
    
    public void setVehicleId(Long vehicleId) {
    	this.vehicleId = vehicleId;
    }
    
    public Long getVehicleId() {
    	return this.vehicleId;
    }
    
    public void setTerminalPhone(String terminalPhone) {
    	this.terminalPhone = terminalPhone;
    }
    
   public String getTerminalPhone() {
	   return this.terminalPhone;
   }
   
   public void setVin(String vin) {
   	this.vin = vin;
   }
   
  public String getVin() {
	   return this.vin;
  }
  
  
  
  
  public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	  public void setSampleTime(ZonedDateTime sampleTime) {
		  this.sampleTime = sampleTime;
	  }

	  public ZonedDateTime getSampleTime() {
		  return this.sampleTime;
	  }
	
	public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public Long getAlarmInfo() {
        return alarmInfo;
    }

    public void setAlarmInfo(Long alarmInfo) {
        this.alarmInfo = alarmInfo;
    }

    public Boolean getTemperatureDifferential() {
        return temperatureDifferential;
    }

    public void setTemperatureDifferential(Boolean temperatureDifferential) {
        this.temperatureDifferential = temperatureDifferential;
    }

    public Boolean getBatteryHighTemperature() {
        return batteryHighTemperature;
    }

    public void setBatteryHighTemperature(Boolean batteryHighTemperature) {
        this.batteryHighTemperature = batteryHighTemperature;
    }

    public Boolean getDeviceTypeOverVoltage() {
        return deviceTypeOverVoltage;
    }

    public void setDeviceTypeOverVoltage(Boolean deviceTypeOverVoltage) {
        this.deviceTypeOverVoltage = deviceTypeOverVoltage;
    }

    public Boolean getDeviceTypeUnderVoltage() {
        return deviceTypeUnderVoltage;
    }

    public void setDeviceTypeUnderVoltage(Boolean deviceTypeUnderVoltage) {
        this.deviceTypeUnderVoltage = deviceTypeUnderVoltage;
    }

    public Boolean getSocLow() {
        return socLow;
    }

    public void setSocLow(Boolean socLow) {
        this.socLow = socLow;
    }

    public Boolean getMonomerBatteryOverVoltage() {
        return monomerBatteryOverVoltage;
    }

    public void setMonomerBatteryOverVoltage(Boolean monomerBatteryOverVoltage) {
        this.monomerBatteryOverVoltage = monomerBatteryOverVoltage;
    }

    public Boolean getMonomerBatteryUnderVoltage() {
        return monomerBatteryUnderVoltage;
    }

    public void setMonomerBatteryUnderVoltage(Boolean monomerBatteryUnderVoltage) {
        this.monomerBatteryUnderVoltage = monomerBatteryUnderVoltage;
    }

    public Boolean getSocHigh() {
        return socHigh;
    }

    public void setSocHigh(Boolean socHigh) {
        this.socHigh = socHigh;
    }

    public Boolean getSocJump() {
        return socJump;
    }

    public void setSocJump(Boolean socJump) {
        this.socJump = socJump;
    }

    public Boolean getDeviceTypeDontMatch() {
        return deviceTypeDontMatch;
    }

    public void setDeviceTypeDontMatch(Boolean deviceTypeDontMatch) {
        this.deviceTypeDontMatch = deviceTypeDontMatch;
    }

    public Boolean getBatteryConsistencyPoor() {
        return batteryConsistencyPoor;
    }

    public void setBatteryConsistencyPoor(Boolean batteryConsistencyPoor) {
        this.batteryConsistencyPoor = batteryConsistencyPoor;
    }

    public Boolean getInsulation() {
        return insulation;
    }

    public void setInsulation(Boolean insulation) {
        this.insulation = insulation;
    }

    public Boolean getDcTemperature() {
        return dcTemperature;
    }

    public void setDcTemperature(Boolean dcTemperature) {
        this.dcTemperature = dcTemperature;
    }

    public Boolean getBrakingSystem() {
        return brakingSystem;
    }

    public void setBrakingSystem(Boolean brakingSystem) {
        this.brakingSystem = brakingSystem;
    }

    public Boolean getDcStatus() {
        return dcStatus;
    }

    public void setDcStatus(Boolean dcStatus) {
        this.dcStatus = dcStatus;
    }

    public Boolean getDriveMotorControllerTemperature() {
        return driveMotorControllerTemperature;
    }

    public void setDriveMotorControllerTemperature(Boolean driveMotorControllerTemperature) {
        this.driveMotorControllerTemperature = driveMotorControllerTemperature;
    }

    public Boolean getHighPressureInterlock() {
        return highPressureInterlock;
    }

    public void setHighPressureInterlock(Boolean highPressureInterlock) {
        this.highPressureInterlock = highPressureInterlock;
    }

    public Boolean getDriveMotorTemperature() {
        return driveMotorTemperature;
    }

    public void setDriveMotorTemperature(Boolean driveMotorTemperature) {
        this.driveMotorTemperature = driveMotorTemperature;
    }

    public Boolean getDeviceTypeOverFilling() {
        return deviceTypeOverFilling;
    }

    public void setDeviceTypeOverFilling(Boolean deviceTypeOverFilling) {
        this.deviceTypeOverFilling = deviceTypeOverFilling;
    }

    public Short getDeviceFailureCount() {
        return deviceFailureCount;
    }

    public void setDeviceFailureCount(Short deviceFailureCount) {
        this.deviceFailureCount = deviceFailureCount;
    }

    public List<Long> getDeviceFailureCodes() {
        return deviceFailureCodes;
    }

    public void setDeviceFailureCodes(List<Long> deviceFailureCodes) {
        this.deviceFailureCodes = deviceFailureCodes;
    }

    public Short getDriveMotorFailureCount() {
        return driveMotorFailureCount;
    }

    public void setDriveMotorFailureCount(Short driveMotorFailureCount) {
        this.driveMotorFailureCount = driveMotorFailureCount;
    }

    public List<Long> getDriveMotorFailureCodes() {
        return driveMotorFailureCodes;
    }

    public void setDriveMotorFailureCodes(List<Long> driveMotorFailureCodes) {
        this.driveMotorFailureCodes = driveMotorFailureCodes;
    }

    public Short getEngineFailureCount() {
        return engineFailureCount;
    }

    public void setEngineFailureCount(Short engineFailureCount) {
        this.engineFailureCount = engineFailureCount;
    }

    public List<Long> getEngineFailureCodes() {
        return engineFailureCodes;
    }

    public void setEngineFailureCodes(List<Long> engineFailureCodes) {
        this.engineFailureCodes = engineFailureCodes;
    }

    public Short getOtherFailure() {
        return otherFailure;
    }

    public void setOtherFailure(Short otherFailure) {
        this.otherFailure = otherFailure;
    }

    public List<Long> getOtherFailureCodes() {
        return otherFailureCodes;
    }

    public void setOtherFailureCodes(List<Long> otherFailureCodes) {
        this.otherFailureCodes = otherFailureCodes;
    }

}
