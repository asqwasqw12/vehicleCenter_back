package com.eshop.pojo;

public class VehicleLocation {
	
	private Long vehicleId;		//车辆Id
	private Integer alarm;       // 告警信息
    private Integer statusField; //状态
    private Float latitude;		//经度
    private Float longitude;	//纬度
    private Short elevation;    //海拔
    private Short speed;		//速度
    private Short direction;	//方向
    private String time;		//时间
    
    public void setVehicleId(Long vehicleId) {
    	this.vehicleId = vehicleId;
    }
    
    public Long getVehicleId() {
    	return this.vehicleId;
    }
    
   
   public void setAlarm(Integer alarm) {
	   this.alarm = alarm;
   }
   
   public Integer getAlarm() {
	   return this.alarm;
   }

   public void setStatusField(Integer statusField) {
	   this.statusField = statusField;
   }
   
   public Integer getStatusField() {
	   return this.statusField;
   }
   
   public void setLatitude(float latitude) {
	   this.latitude = latitude;
   }
   
   public float getLatitude() {
	   return this.latitude;
   }
   
   public void setLongitude(float longitude) {
	   this.longitude = longitude;
   }
   
   public float getLongitude() {
	   return this.longitude;
   }
   
   public void setElevation(short elevation) {
	   this.elevation = elevation;
   }
   
   public short getElevation() {
	   return this.elevation;
   }
   
   public void setSpeed(short speed) {
	   this.speed = speed;
   }
   
   public short getSpeed() {
	   return this.speed;
   }
   
   public void setDirection(short direction) {
	   this.direction = direction;
   }
   
   public short getDirection() {
	   return this.direction;
   }
   
   public void setTime(String time) {
	   this.time = time;
   }
   
   public String getTime() {
	   return this.time;
   }

}