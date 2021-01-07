package com.eshop.gateway.gb32960.pojo;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class FuelCellData {
	
	Long id; //
	
	private Long vehicleId;			//车辆Id
	
	private String terminalPhone; // 终端手机号
	
	private String vin; //车辆唯一识别码
	
    //燃料电池电压
    private Integer voltage;

    //燃料电池电流
    private Integer current;

    //燃料消耗率
    private Integer fuelConsumption;

    //燃料电池温度探针总数
    private Integer temperatureProbeCount;

    //探针温度值集合
    private List<Short> probeTemperature;

    //氢系统中最高温度
    private Integer hydrogenSystemMaxTemperature;

    //氢系统中最高温度探针代号
    private Short hydrogenSystemTemperatureProbeNum;

    //氢气最高浓度
    private Integer hydrogenSystemMaxConcentration;

    //氢气最高浓度传感器代号
    private Short hydrogenSystemConcentrationProbeNum;

    //氢气最高压力
    private Integer hydrogenSystemMaxPressure;

    //氢气最高压力传感器代号
    private Short hydrogenSystemPressureProbeNum;

    //高压DC/DC状态
    private Short dcStatus;
    
    private Date createTime;	//创建时间
    
	//数据采集时间
	private LocalDateTime sampleTime;//采样时间
    
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
	
	  public void setSampleTime(LocalDateTime sampleTime) {
		  this.sampleTime = sampleTime;
	  }

	  public LocalDateTime getSampleTime() {
		  return this.sampleTime;
	  }
	
	public Integer getVoltage() {
        return voltage;
    }

    public void setVoltage(Integer voltage) {
        this.voltage = voltage;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Integer fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public Integer getTemperatureProbeCount() {
        return temperatureProbeCount;
    }

    public void setTemperatureProbeCount(Integer temperatureProbeCount) {
        this.temperatureProbeCount = temperatureProbeCount;
    }

    public List<Short> getProbeTemperature() {
        return probeTemperature;
    }

    public void setProbeTemperature(List<Short> probeTemperature) {
        this.probeTemperature = probeTemperature;
    }

    public Integer getHydrogenSystemMaxTemperature() {
        return hydrogenSystemMaxTemperature;
    }

    public void setHydrogenSystemMaxTemperature(Integer hydrogenSystemMaxTemperature) {
        this.hydrogenSystemMaxTemperature = hydrogenSystemMaxTemperature;
    }

    public Short getHydrogenSystemTemperatureProbeNum() {
        return hydrogenSystemTemperatureProbeNum;
    }

    public void setHydrogenSystemTemperatureProbeNum(Short hydrogenSystemTemperatureProbeNum) {
        this.hydrogenSystemTemperatureProbeNum = hydrogenSystemTemperatureProbeNum;
    }

    public Integer getHydrogenSystemMaxConcentration() {
        return hydrogenSystemMaxConcentration;
    }

    public void setHydrogenSystemMaxConcentration(Integer hydrogenSystemMaxConcentration) {
        this.hydrogenSystemMaxConcentration = hydrogenSystemMaxConcentration;
    }

    public Short getHydrogenSystemConcentrationProbeNum() {
        return hydrogenSystemConcentrationProbeNum;
    }

    public void setHydrogenSystemConcentrationProbeNum(Short hydrogenSystemConcentrationProbeNum) {
        this.hydrogenSystemConcentrationProbeNum = hydrogenSystemConcentrationProbeNum;
    }

    public Integer getHydrogenSystemMaxPressure() {
        return hydrogenSystemMaxPressure;
    }

    public void setHydrogenSystemMaxPressure(Integer hydrogenSystemMaxPressure) {
        this.hydrogenSystemMaxPressure = hydrogenSystemMaxPressure;
    }

    public Short getHydrogenSystemPressureProbeNum() {
        return hydrogenSystemPressureProbeNum;
    }

    public void setHydrogenSystemPressureProbeNum(Short hydrogenSystemPressureProbeNum) {
        this.hydrogenSystemPressureProbeNum = hydrogenSystemPressureProbeNum;
    }

    public Short getDcStatus() {
        return dcStatus;
    }

    public void setDcStatus(Short dcStatus) {
        this.dcStatus = dcStatus;
    }

}
