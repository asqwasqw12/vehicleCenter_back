package com.eshop.gateway.gb32960.service;

import java.util.List;

import com.eshop.gateway.gb32960.pojo.DriveMotorData;
import com.eshop.sys.service.CurdService;

public interface DriveMotorDataService extends CurdService<DriveMotorData>{
	//保存驱动电机信息
	public int  saveDriveMotorData(DriveMotorData driveMotorData);
	
	public List<DriveMotorData> findByVehicleId(Long id);
}
