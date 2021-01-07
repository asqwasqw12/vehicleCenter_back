package com.eshop.gateway.gb32960.pojo;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

public class DriveMotorData {
	
	Long id; //
	
	private Long vehicleId;			//车辆Id
	
	private String terminalPhone; // 终端手机号
	
	private String vin; //车辆唯一识别码

	private Date createTime;	//创建时间
	
	//数据采集时间
	private LocalDateTime sampleTime;//采样时间
	
	 //驱动电机序号,有效值范围：0-253
    private Short num;

    //驱动电机状态,01：耗电，02：发电，03：关闭状态，04：准备状态，FE:表示异常，FF:表示无效
    private Short status;

    //驱动电机控制温度，有效值范围：0-250，偏移量：-40，系数：1摄氏度，FE:表示异常，FF:表示无效
    private Short controllerTemperature;

    //驱动电机转速,有效值范围：0-65531，偏移量：-20000，系数：1r/min,FE:表示异常，FF:表示无效
    private Integer speed;

    //驱动电机转矩,有效值范围：0-65531，偏移量：-20000，系数：0.1Nm，0xFFFE：表示异常，0xFFFF:表示无效
    private Integer torque;

    //驱动电机温度，有效值范围：0-250,偏移量：-40，系数：1摄氏度，FE:表示异常，FF:表示无效
    private Short temperature;

    //电机控制器输入电压，有效值范围：0-60000，系数：0.1V，0xFFFE：表示异常，0xFFFF:表示无效
    private Integer controllerInputVoltage;

    //电机控制器直流母线电流，有效值范围：0-20000，偏移量：-10000，系数：0.1A,0xFFFE：表示异常，0xFFFF:表示无效
    private Integer controllerBusCurrent;
	
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
	
	public Short getNum() {
        return num;
    }

    public void setNum(Short num) {
        this.num = num;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getControllerTemperature() {
        return controllerTemperature;
    }

    public void setControllerTemperature(Short controllerTemperature) {
        this.controllerTemperature = controllerTemperature;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getTorque() {
        return torque;
    }

    public void setTorque(Integer torque) {
        this.torque = torque;
    }

    public Short getTemperature() {
        return temperature;
    }

    public void setTemperature(Short temperature) {
        this.temperature = temperature;
    }

    public Integer getControllerInputVoltage() {
        return controllerInputVoltage;
    }

    public void setControllerInputVoltage(Integer controllerInputVoltage) {
        this.controllerInputVoltage = controllerInputVoltage;
    }

    public Integer getControllerBusCurrent() {
        return controllerBusCurrent;
    }

    public void setControllerBusCurrent(Integer controllerBusCurrent) {
        this.controllerBusCurrent = controllerBusCurrent;
    }

}
