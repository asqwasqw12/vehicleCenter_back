package com.eshop.gateway.gb32960.pojo;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

public class RunData {
	
	Long id; //
	
	private Long vehicleId;			//车辆Id
	
	private String terminalPhone; // 终端手机号
	
	private String vin; //车辆唯一识别码

	//车辆运行状态,01:车辆启动，02：熄火，03：其他状态，FE:表示异常，FF:表示无效
    private Short runStatus;

    //充电状态，01:停车充电，02：行驶充电，03：未充电状态，04：充电完成，FE:表示异常，FF:表示无效
    private Short chargeStatus;

    //运行模式，01：纯电，02：混动，03：燃油，FE:表示异常，FF:表示无效
    private Short operationMode;

    //车速，有效值范围：0-2200,系数：0.1km/h,FE:表示异常，FF:表示无效
    private Integer speed;

    //累计里程，有效值范围：0-9,999,999，系数：0.1km,FFFFFE：表示异常，FFFFFF:表示无效
    private Long mileAge;

    //总电压，有效值范围：0-10000，系数：0.1V,FFFE:表示异常，FFFF表示无效
    private Integer totalVoltage;

    //总电流，0-2000，偏移量：-1000，系数：0.1A,FFFE:表示异常，FFFF表示无效
    private Integer totalCurrent;

    //SOC,有效值范围：0-100，系数：1%，FE:表示异常，FF:表示无效
    private Short soc;

    //DC-DC状态，01：工作，02：断开，FE:表示异常，FF:表示无效
    private Short dcStatus;

    //挡位，bit0-3位，0000：空挡，001:1档，。。。1100：12档，1101：倒挡，1110：自动D档，1111:停车P档，bit4,0:无制动力,1:有制动力，bit5,0:无驱动力,1:有驱动力，bit6-7预留
    private Short gears;

    //绝缘电阻,有效值范围：0-60000，系数1k欧姆
    private Integer insulationResistance;

    //加速踏板行程值，有效值范围：0-100，系数：1%，FE:表示异常，FF:表示无效
    private Short throttle;
    
    //制动踏板，有效值范围：0-100，系数：1%，0：制动关的状态，0x65即“101”表示制动有效状态，FE:表示异常，FF:表示无效
    private Short brake;
    
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
    
    public Short getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(Short runStatus) {
        this.runStatus = runStatus;
    }

    public Short getChargeStatus() {
        return chargeStatus;
    }

    public void setChargeStatus(Short chargeStatus) {
        this.chargeStatus = chargeStatus;
    }

    public Short getOperationMode() {
        return operationMode;
    }

    public void setOperationMode(Short operationMode) {
        this.operationMode = operationMode;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Long getMileAge() {
        return mileAge;
    }

    public void setMileAge(Long mileAge) {
        this.mileAge = mileAge;
    }

    public Integer getTotalVoltage() {
        return totalVoltage;
    }

    public void setTotalVoltage(Integer totalVoltage) {
        this.totalVoltage = totalVoltage;
    }

    public Integer getTotalCurrent() {
        return totalCurrent;
    }

    public void setTotalCurrent(Integer totalCurrent) {
        this.totalCurrent = totalCurrent;
    }

    public Short getSoc() {
        return soc;
    }

    public void setSoc(Short soc) {
        this.soc = soc;
    }

    public Short getDcStatus() {
        return dcStatus;
    }

    public void setDcStatus(Short dcStatus) {
        this.dcStatus = dcStatus;
    }

    public Short getGears() {
        return gears;
    }

    public void setGears(Short gears) {
        this.gears = gears;
    }

    public Integer getInsulationResistance() {
        return insulationResistance;
    }

    public void setInsulationResistance(Integer insulationResistance) {
        this.insulationResistance = insulationResistance;
    }
    
    public Short getThrottle() {
        return throttle;
    }

    public void setThrottle(Short throttle) {
        this.throttle = throttle;
    }
    
    public Short getBrake() {
        return brake;
    }

    public void setBrake(Short brake) {
        this.brake = brake;
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
   // public Integer getReserve() {
   //     return reserve;
   // }

   // public void setReserve(Integer reserve) {
   //     this.reserve = reserve;
   // }
}
