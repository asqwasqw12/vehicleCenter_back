package com.eshop.pojo;

public class VehicleDevice extends BasePojo {
	
	private Long vehicleId;  //车辆Id
	private Long deviceId;   //设备Id
	private int isTerminal;	 //是否为数据通讯终端
	private String terminalPhone;    //终端手机号
	
	public void setVehicleId(Long vehicleId) {
    	this.vehicleId = vehicleId;
    }
   
    public Long getVehicleId() {
    	return vehicleId;
    }
    
    public void setDeviceId(Long deviceId) {
    	this.deviceId = deviceId;
    }
   
    public Long getDeviceId() {
    	return deviceId;
    }
    
    public void setIsTerminal(int isTerminal) {
		this.isTerminal = isTerminal;
	}
    
    public int getIsTerminalr() {
		return isTerminal;
	}
    
    public void setTerminalPhone(String terminalPhone) {
    	this.terminalPhone  = terminalPhone;
    }
    
    public String getTerminalPhone() {
    	return terminalPhone;
    }
	
	
}
