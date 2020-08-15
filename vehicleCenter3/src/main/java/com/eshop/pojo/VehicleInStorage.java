package com.eshop.pojo;

import java.util.Date;

public class VehicleInStorage extends BasePojo{
	private Long vehicleId;	//车辆编号
	private String operator;	//入库经办人
	private Date inTime; //入库时间
	private String storageName; //库房名称
	
    
    
    
    public void setVehicleId(Long vehicleId) {
    	this.vehicleId = vehicleId;
    }
   
    public Long getVehicleId() {
    	return vehicleId;
    }
    
    public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}
	
	public String getoperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
    


}
