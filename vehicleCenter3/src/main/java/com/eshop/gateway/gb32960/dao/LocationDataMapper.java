package com.eshop.gateway.gb32960.dao;

import java.util.List;

import com.eshop.gateway.gb32960.pojo.LocationData;


public interface LocationDataMapper {
	
 	int deleteByPrimaryKey(Long id);

    int insert(LocationData record);

    int insertSelective(LocationData record);

    LocationData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LocationData record);

    int updateByPrimaryKey(LocationData record);
    
    List<LocationData> findByVehicleId(Long id); 
    
    List<LocationData> findAll();

}
