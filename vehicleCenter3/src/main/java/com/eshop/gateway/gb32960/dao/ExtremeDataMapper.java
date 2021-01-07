package com.eshop.gateway.gb32960.dao;

import java.util.List;

import com.eshop.gateway.gb32960.pojo.ExtremeData;



public interface ExtremeDataMapper {

	int deleteByPrimaryKey(Long id);

    int insert(ExtremeData record);

    int insertSelective(ExtremeData record);

    ExtremeData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExtremeData record);

    int updateByPrimaryKey(ExtremeData record);
    
    List<ExtremeData> findByVehicleId(Long id); 
    
    List<ExtremeData> findAll();
}
