package com.eshop.gateway.gb32960.dao;

import java.util.List;

import com.eshop.gateway.gb32960.pojo.DriveMotorData;


public interface DriveMotorDataMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(DriveMotorData record);

    int insertSelective(DriveMotorData record);

    DriveMotorData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DriveMotorData record);

    int updateByPrimaryKey(DriveMotorData record);
    
    List<DriveMotorData> findByVehicleId(Long id); 
    
    List<DriveMotorData> findAll();

}
