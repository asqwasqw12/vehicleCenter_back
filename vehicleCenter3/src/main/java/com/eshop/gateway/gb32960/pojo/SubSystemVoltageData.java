package com.eshop.gateway.gb32960.pojo;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class SubSystemVoltageData {
	
	Long id; //
	
	private Long vehicleId;			//车辆Id
	
	private String terminalPhone; // 终端手机号
	
	private String vin; //车辆唯一识别码

	private Date createTime;	//创建时间
	
	//数据采集时间
	private ZonedDateTime sampleTime;//采样时间
	
    //子系统号
    private Short num;

    //电压
    private Integer voltage;

    //电流
    private Integer current;

    //单体电池个数
    private Integer cellCount;

    //
    private Integer batteryNumber;

     //本帧单体电池总数
    private Short batteryCount;

    //单体电池电压列表
    private List<Integer> cellVoltages;
	
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

    public Integer getCellCount() {
        return cellCount;
    }

    public void setCellCount(Integer cellCount) {
        this.cellCount = cellCount;
    }

    public Integer getBatteryNumber() {
        return batteryNumber;
    }

    public void setBatteryNumber(Integer batteryNumber) {
        this.batteryNumber = batteryNumber;
    }

    public Short getBatteryCount() {
        return batteryCount;
    }

    public void setBatteryCount(Short batteryCount) {
        this.batteryCount = batteryCount;
    }

    public List<Integer> getCellVoltages() {
        return cellVoltages;
    }

    public void setCellVoltages(List<Integer> cellVoltages) {
        this.cellVoltages = cellVoltages;
    }

}
