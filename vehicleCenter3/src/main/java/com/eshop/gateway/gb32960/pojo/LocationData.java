package com.eshop.gateway.gb32960.pojo;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

public class LocationData {
	
	Long id; //
	
	private Long vehicleId;			//车辆Id
	
	private String terminalPhone; // 终端手机号
	
	private String vin; //车辆唯一识别码
	
	//定位状态，bit0,0:有效定位，1：无效定位，bit1,0:北纬，1南纬，bit2,0:东经，1：西经
    private Short status;
    
    //经度
    private Double longitude;

    //纬度
    private Double latitude;
    
    private Date createTime;	//创建时间
    
	//数据采集时间
	private LocalDateTime sampleTime;//采样时间
    
    
    //非数据库数据   
    private Boolean valid;//是否有效定位  
    private Integer longitudeDataType; //经度类型，0：东经，1：西经  
    private Integer latitudeDataType;//纬度类型，0：北纬，1：南纬
  //数据采集时间
  	private ZonedDateTime sampleZonedTime;//采样时间
 
	
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
  
  public Short getStatus() {
      return status;
  }

  public void setStatus(Short status) {
      this.status = status;
  }
  
  public Double getLongitude() {
      return longitude;
  }

  public void setLongitude(Double longitude) {
      this.longitude = longitude;
  }

  public Double getLatitude() {
      return latitude;
  }

  public void setLatitude(Double latitude) {
      this.latitude = latitude;
  }
  
  public Boolean getValid() {
      return valid;
  }

  public void setValid(Boolean valid) {
      this.valid = valid;
  }
  
  public Integer getLongitudeDataType() {
      return longitudeDataType;
  }

  public void setLongitudeDataType(Integer longitudeDataType) {
      this.longitudeDataType = longitudeDataType;
  }

  public Integer getLatitudeDataType() {
      return latitudeDataType;
  }

  public void setLatitudeDataType(Integer latitudeDataType) {
      this.latitudeDataType = latitudeDataType;
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
	  
	  public void setSampleZonedTime(ZonedDateTime sampleZonedTime) {
		  this.sampleZonedTime = sampleZonedTime;
	  }

	  public ZonedDateTime getSampleZonedTime() {
		  return this.sampleZonedTime;
	  }
}
