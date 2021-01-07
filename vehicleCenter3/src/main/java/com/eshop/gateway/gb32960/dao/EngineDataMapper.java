package com.eshop.gateway.gb32960.dao;

import java.util.List;

import com.eshop.gateway.gb32960.pojo.EngineData;


public interface EngineDataMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(EngineData record);

    int insertSelective(EngineData record);

    EngineData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EngineData record);

    int updateByPrimaryKey(EngineData record);
    
    List<EngineData> findByVehicleId(Long id); 
    
    List<EngineData> findAll();

}
