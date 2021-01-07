package com.eshop.gateway.gb32960.dao;

import java.util.List;

import com.eshop.gateway.gb32960.pojo.RunData;



public interface RunDataMapper {
	int deleteByPrimaryKey(Long id);

    int insert(RunData record);

    int insertSelective(RunData record);

    RunData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RunData record);

    int updateByPrimaryKey(RunData record);
    
    List<RunData> findByVehicleId(Long id); 
    
    List<RunData> findAll();
}
