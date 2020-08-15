package com.eshop.service;

import java.util.List;

import com.eshop.pojo.VehicleDevice;
import com.eshop.sys.service.CurdService;

public interface VehicleDeviceService extends CurdService<VehicleDevice> {
	
	/**
	 * 查询全部
	 * @return
	 */
	VehicleDevice findAll();
	
	//根据车辆查询
	List<VehicleDevice> findByVehicleId(Long vehicleId);
	
	//根据设备号查询
	VehicleDevice   findByDeviceId(Long deviceId);
	
	//根据手机号查询
	VehicleDevice findByTerminalPhone(String terminalPhone);

}
