package com.eshop.gateway.gb32960.dao;

import java.util.List;

import com.eshop.gateway.gb32960.pojo.SubSystemTemperatureData;



public interface SubSystemTemperatureDataMapper {
	int deleteByPrimaryKey(Long id);

    int insert(SubSystemTemperatureData record);

    int insertSelective(SubSystemTemperatureData record);

    SubSystemTemperatureData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubSystemTemperatureData record);

    int updateByPrimaryKey(SubSystemTemperatureData record);
    
    List<SubSystemTemperatureData> findByVehicleId(Long id); 
    
    List<SubSystemTemperatureData> findAll();
}
