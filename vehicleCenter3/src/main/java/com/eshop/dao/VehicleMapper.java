package com.eshop.dao;

import java.util.List;
import java.util.Map;

import com.eshop.pojo.Vehicle;

public interface VehicleMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(Vehicle record);

    int insertSelective(Vehicle record);

    Vehicle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Vehicle record);

    int updateByPrimaryKey(Vehicle record);
    
    List<Vehicle> findPageByParams(Map<String,Object> params);

}
