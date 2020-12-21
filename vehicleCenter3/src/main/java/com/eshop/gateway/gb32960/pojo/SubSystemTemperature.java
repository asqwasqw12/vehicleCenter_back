package com.eshop.gateway.gb32960.pojo;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class SubSystemTemperature {

	Long id; //
	
	private Long vehicleId;			//车辆Id
	
	private String terminalPhone; // 终端手机号
	
	private String vin; //车辆唯一识别码

	private Date createTime;	//创建时间
	
	//数据采集时间
	private ZonedDateTime sampleTime;//采样时间
	
    // 子系统号
    private Short num;

    //温度探针个数
    private Integer temperatureProbeCount;

    //探针温度值列表
    private List<Short> probeTemperatures;
	
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

    public Short getNum() {
        return num;
    }

    public void setNum(Short num) {
        this.num = num;
    }

    public Integer getTemperatureProbeCount() {
        return temperatureProbeCount;
    }

    public void setTemperatureProbeCount(Integer temperatureProbeCount) {
        this.temperatureProbeCount = temperatureProbeCount;
    }

    public List<Short> getProbeTemperatures() {
        return probeTemperatures;
    }

    public void setProbeTemperatures(List<Short> probeTemperatures) {
        this.probeTemperatures = probeTemperatures;
    }
}
