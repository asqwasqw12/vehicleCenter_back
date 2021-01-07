package com.eshop.gateway.gb32960.pojo;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

public class EngineData {
	
	Long id; //
	
	private Long vehicleId;			//车辆Id
	
	private String terminalPhone; // 终端手机号
	
	private String vin; //车辆唯一识别码

	private Date createTime;	//创建时间
	
	//数据采集时间
	private LocalDateTime sampleTime;//采样时间
	
	//发动机状态,01:启动状态，02：关闭状态，FE:表示异常，FF:表示无效
    private Short Status;

    //曲轴转速，有效值范围：0-60000，系数：1r/min,0xFFFE：表示异常，OxFFFF:表示无效
    private Integer crankshaftSpeed;

    //燃料消耗率，0-60000，系数：0.01L/100km,0xFFFE：表示异常，OxFFFF:表示无效
    private Integer fuelConsumption;

    public Short getStatus() {
        return Status;
    }

    public void setStatus(Short status) {
        Status = status;
    }

    public Integer getCrankshaftSpeed() {
        return crankshaftSpeed;
    }

    public void setCrankshaftSpeed(Integer crankshaftSpeed) {
        this.crankshaftSpeed = crankshaftSpeed;
    }

    public Integer getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Integer fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
	
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
	
}
