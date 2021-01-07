package com.eshop.gateway.gb32960.dao;

import java.util.List;

import com.eshop.gateway.gb32960.pojo.SubSystemVoltageData;


public interface SubSystemVoltageDataMapper {
	int deleteByPrimaryKey(Long id);

    int insert(SubSystemVoltageData record);

    int insertSelective(SubSystemVoltageData record);

    SubSystemVoltageData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubSystemVoltageData record);

    int updateByPrimaryKey(SubSystemVoltageData record);
    
    List<SubSystemVoltageData> findByVehicleId(Long id); 
    
    List<SubSystemVoltageData> findAll();
}
