package com.eshop.gateway.gb32960.dao;

import java.util.List;

import com.eshop.gateway.gb32960.pojo.AlarmData;


public interface AlarmDataMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(AlarmData record);

    int insertSelective(AlarmData record);

    AlarmData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AlarmData record);

    int updateByPrimaryKey(AlarmData record);
    
    List<AlarmData> findByVehicleId(Long id); 
    
    List<AlarmData> findAll();

}
