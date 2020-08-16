package com.eshop.dao;

import java.util.List;
import java.util.Map;

import com.eshop.pojo.VehicleDevice;

public interface VehicleDeviceMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(VehicleDevice record);

    int insertSelective(VehicleDevice record);

    VehicleDevice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VehicleDevice record);

    int updateByPrimaryKey(VehicleDevice record);
    
    List<VehicleDevice> findByVehicleId(Long vehicleId); 
    
    VehicleDevice findByDeviceId(Long deviceId); 
    
    VehicleDevice findByTerminalPhone(String terminalPhone); 
    
    List<VehicleDevice> findAll();
    
    List<VehicleDevice> findPageByParams(Map<String,Object> params);

}
