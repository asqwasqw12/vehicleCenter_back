package com.eshop.gateway.gb32960.pojo;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class VehicleRealTimeData {
	
	Long id; //
	
	private Long vehicleId;			//车辆Id
	
	private String terminalPhone; // 终端手机号
	
	private String vin; //车辆唯一识别码

	//数据采集时间
	private LocalDateTime sampleTime;//采样时间

    //整车数据
    private RunData runData;

    //驱动电机个数
    private Short driveMotorCount;

    //驱动电机数据列表
    private List<DriveMotorData> driveMotorDatas;

    //燃料电池数据
    private FuelCellData fuelCellData;

    //发动机数据
    private EngineData engineData;

    //位置数据
    private LocationData locationData;

    //极值数据
    private ExtremeData extremeData;

    //报警数据
    private AlarmData alarmData;

    //可充电储能装置电压数据个数
    private Short subsystemVoltageCount;

    //可充电储能装置电压数据列表
    private List<SubSystemVoltageData> subSystemVoltageDatas;

    //可充电储能装置温度数据个数
    private Short subsystemTemperatureCount;

    //可充电储能装置温度数据列表
    private List<SubSystemTemperatureData> subSystemTemperatures;
    
    private Date createTime;	//创建时间
    
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

  
  public void setSampleTime(LocalDateTime sampleTime) {
	  this.sampleTime = sampleTime;
  }

  public LocalDateTime getSampleTime() {
	  return this.sampleTime;
  }
    public RunData getRunData() {
        return runData;
    }

    public void setRunData(RunData runData) {
        this.runData = runData;
    }

    public Short getDriveMotorCount() {
        return driveMotorCount;
    }

    public void setDriveMotorCount(Short driveMotorCount) {
        this.driveMotorCount = driveMotorCount;
    }

    public List<DriveMotorData> getDriveMotorDatas() {
        return driveMotorDatas;
    }

    public void setDriveMotorDatas(List<DriveMotorData> driveMotorDatas) {
        this.driveMotorDatas = driveMotorDatas;
    }

    public FuelCellData getFuelCellData() {
        return fuelCellData;
    }

    public void setFuelCellData(FuelCellData fuelCellData) {
        this.fuelCellData = fuelCellData;
    }

    public EngineData getEngineData() {
        return engineData;
    }

    public void setEngineData(EngineData engineData) {
        this.engineData = engineData;
    }

    public LocationData getLocationData() {
        return locationData;
    }

    public void setLocationData(LocationData locationData) {
        this.locationData = locationData;
    }

    public ExtremeData getExtremeData() {
        return extremeData;
    }

    public void setExtremeData(ExtremeData extremeData) {
        this.extremeData = extremeData;
    }

    public AlarmData getAlarmData() {
        return alarmData;
    }

    public void setAlarmData(AlarmData alarmData) {
        this.alarmData = alarmData;
    }

    public Short getSubsystemVoltageCount() {
        return subsystemVoltageCount;
    }

    public void setSubsystemVoltageCount(Short subsystemVoltageCount) {
        this.subsystemVoltageCount = subsystemVoltageCount;
    }

    public List<SubSystemVoltageData> getSubSystemVoltageDatas() {
        return subSystemVoltageDatas;
    }

    public void setSubSystemVoltageDatas(List<SubSystemVoltageData> subSystemVoltageDatas) {
        this.subSystemVoltageDatas = subSystemVoltageDatas;
    }

    public Short getSubsystemTemperatureCount() {
        return subsystemTemperatureCount;
    }

    public void setSubsystemTemperatureCount(Short subsystemTemperatureCount) {
        this.subsystemTemperatureCount = subsystemTemperatureCount;
    }

    public List<SubSystemTemperatureData> getSubSystemTemperatures() {
        return subSystemTemperatures;
    }

    public void setSubSystemTemperatures(List<SubSystemTemperatureData> subSystemTemperatures) {
        this.subSystemTemperatures = subSystemTemperatures;
    }
    
    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
