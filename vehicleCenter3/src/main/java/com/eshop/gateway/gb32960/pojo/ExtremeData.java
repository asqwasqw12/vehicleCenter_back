package com.eshop.gateway.gb32960.pojo;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

public class ExtremeData {
	
	Long id; //
	
	private Long vehicleId;			//车辆Id
	
	private String terminalPhone; // 终端手机号
	
	private String vin; //车辆唯一识别码

	private Date createTime;	//创建时间
	
	//数据采集时间
	private LocalDateTime sampleTime;//采样时间
	
	//最高电压电池子系统号,有效值范围：1-250，FE:表示异常，FF:表示无效
    private Short maxVoltageSystemNum;

    //最高电压电池单体代号，有效值范围：1-250，FE:表示异常，FF:表示无效
    private Short maxVoltageBatteryNum;

    //电池单体电压最高值，有效值范围：0-15000，系数：0.001V,0xFFFE:表示异常，0xFFFF：表示无效
    private Integer batteryMaxVoltage;

    //最低电压电池子系统号，有效值范围：1-250，FE:表示异常，FF:表示无效
    private Short minVoltageSystemNum;

    //最低电压电池单体代号，有效值范围：1-250，FE:表示异常，FF:表示无效
    private Short minVoltageBatteryNum;

    //电池单体电压最低值，有效值范围：0-15000，系数：0.001V,0xFFFE:表示异常，0xFFFF：表示无效
    private Integer batteryMinVoltage;

    //最高温度子系统号，有效值范围：1-250，FE:表示异常，FF:表示无效
    private Short maxTemperatureSystemNum;

    //最高温度探针序号，有效值范围：1-250，FE:表示异常，FF:表示无效
    private Short maxTemperatureNum;

    //最高温度值，有效值范围：0-250，偏移量：-40，系数：1摄氏度,FE:表示异常，FF:表示无效
    private Short maxTemperature;


    //最低温度子系统号,有效值范围：1-250，FE:表示异常，FF:表示无效
    private Short minTemperatureSystemNum;

    //最低温度探针序号,有效值范围：1-250，FE:表示异常，FF:表示无效
    private Short minTemperatureNum;

    //最低温度值,有效值范围：0-250，偏移量：-40，系数：1摄氏度,FE:表示异常，FF:表示无效
    private Short minTemperature;
	
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
	
	public Short getMaxVoltageSystemNum() {
        return maxVoltageSystemNum;
    }

    public void setMaxVoltageSystemNum(Short maxVoltageSystemNum) {
        this.maxVoltageSystemNum = maxVoltageSystemNum;
    }

    public Short getMaxVoltageBatteryNum() {
        return maxVoltageBatteryNum;
    }

    public void setMaxVoltageBatteryNum(Short maxVoltageBatteryNum) {
        this.maxVoltageBatteryNum = maxVoltageBatteryNum;
    }

    public Integer getBatteryMaxVoltage() {
        return batteryMaxVoltage;
    }

    public void setBatteryMaxVoltage(Integer batteryMaxVoltage) {
        this.batteryMaxVoltage = batteryMaxVoltage;
    }

    public Short getMinVoltageSystemNum() {
        return minVoltageSystemNum;
    }

    public void setMinVoltageSystemNum(Short minVoltageSystemNum) {
        this.minVoltageSystemNum = minVoltageSystemNum;
    }

    public Short getMinVoltageBatteryNum() {
        return minVoltageBatteryNum;
    }

    public void setMinVoltageBatteryNum(Short minVoltageBatteryNum) {
        this.minVoltageBatteryNum = minVoltageBatteryNum;
    }

    public Integer getBatteryMinVoltage() {
        return batteryMinVoltage;
    }

    public void setBatteryMinVoltage(Integer batteryMinVoltage) {
        this.batteryMinVoltage = batteryMinVoltage;
    }

    public Short getMaxTemperatureSystemNum() {
        return maxTemperatureSystemNum;
    }

    public void setMaxTemperatureSystemNum(Short maxTemperatureSystemNum) {
        this.maxTemperatureSystemNum = maxTemperatureSystemNum;
    }

    public Short getMaxTemperatureNum() {
        return maxTemperatureNum;
    }

    public void setMaxTemperatureNum(Short maxTemperatureNum) {
        this.maxTemperatureNum = maxTemperatureNum;
    }

    public Short getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Short maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Short getMinTemperatureSystemNum() {
        return minTemperatureSystemNum;
    }

    public void setMinTemperatureSystemNum(Short minTemperatureSystemNum) {
        this.minTemperatureSystemNum = minTemperatureSystemNum;
    }

    public Short getMinTemperatureNum() {
        return minTemperatureNum;
    }

    public void setMinTemperatureNum(Short minTemperatureNum) {
        this.minTemperatureNum = minTemperatureNum;
    }

    public Short getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Short minTemperature) {
        this.minTemperature = minTemperature;
    }

}
