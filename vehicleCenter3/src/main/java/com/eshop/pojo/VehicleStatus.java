package com.eshop.pojo;

import com.eshop.jt808.pojo.Location;

public class VehicleStatus {
	
	private Long vehicleId;
	private Vehicle vehicle;
	private Location location;
	
	 public void setVehicleId(Long vehicleId) {
	    	this.vehicleId = vehicleId;
	    }
	    
	 public Long getVehicleId() {
	    	return this.vehicleId;
	    }
	 
	 public void setVehicle(Vehicle vehicle) {
	    	this.vehicle = vehicle;
	    }
	    
	 public Vehicle getVehicle() {
	    	return this.vehicle;
	    }
	 public void setLocation(Location location) {
	    	this.location = location;
	    }
	    
	 public Location getLocation() {
	    	return this.location;
	    }

}
