package com.eshop.pojo;

import java.util.Date;

public class VehicleInStorage extends BasePojo{
	private Long vehicleId;	//车辆编号
	private Long userId; //经办人id
	private Date inTime; //入库时间
	private String storageName; //库房名称
	//非数据库字段
	private String operator; //入库经办人
	private String name;	//名称
	private String type;	//型号	
	private String vin;		//车架识别码
	private String companyNum;	//出厂编号
    
    
    
    public void setVehicleId(Long vehicleId) {
    	this.vehicleId = vehicleId;
    }
   
    public Long getVehicleId() {
    	return vehicleId;
    }
    
    public void setUserId(Long userId) {
    	this.userId = userId;
    }
   
    public Long getUserId() {
    	return userId;
    }
    
    public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}
	
	
	
	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
    
	public String getoperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public void setName(String name) {
    	this.name = name;
    }
    
    public String getName() {
    	return name;
    }
    
    
    
    public void setType(String type) {
    	this.type = type;
    }
    
    public String getType() {
    	return type;
    }
    
    public void setVin(String vin) {
    	this.vin = vin;
    }
    
    public String getVin() {
    	return vin;
    }
    
    
    public void setCompanyNum(String companyNum) {
    	this.companyNum = companyNum;
    }
    
    public String getCompanyNum() {
    	return companyNum;
    }


}
