package com.eshop.pojo;

public class DriveMotor {

	private String coolingMethod;//冷却方式
	private Float rateVoltage;//额定电压
	private Float maxCurrent; //最大工作电流
	private String deviceId;//驱动电机序号
	private String type; //驱动电机型号
	private Float peakPower;//驱动电机峰值功率
	private Integer maxSpeed;//最高转速
	private Float peakTorque; //峰值转矩
	private Float maxOutputTorque;//最大输出转矩
	
	public void setCoolingMethod(String coolingMethod) {
		this.coolingMethod = coolingMethod;
	}
	
	public String getCoolingMethod() {
		return coolingMethod;
	}
	
	public void setRateVoltage(Float rateVoltage) {
		this.rateVoltage = rateVoltage;
	}
	
	public Float getRateVoltage() {
		return rateVoltage;
	}
	
	public void setMaxCurrent(Float maxCurrent) {
		this.maxCurrent = maxCurrent;
	}
	
	public Float getMaxCurrent() {
		return maxCurrent;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setPeakPower(Float peakPower) {
		this.peakPower = peakPower;
	}
	
	public Float getPeakPower() {
		return peakPower;
	}
	
	public void setMaxSpeed(Integer maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	public Integer getMaxSpeed() {
		return maxSpeed;
	}
	
	public void setPeakTorque(Float peakTorque) {
		this.peakTorque = peakTorque;
	}
	
	public Float getPeakTorque() {
		return peakTorque;
	}
	
	
	
	public void setMaxOutputTorque(Float maxOutputTorque) {
		this.maxOutputTorque = maxOutputTorque;
	}
	
	public Float getMaxOutputTorque() {
		return maxOutputTorque;
	}
}
