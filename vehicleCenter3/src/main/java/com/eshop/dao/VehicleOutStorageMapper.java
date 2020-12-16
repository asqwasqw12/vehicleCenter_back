package com.eshop.dao;

import java.util.List;
import java.util.Map;

import com.eshop.pojo.VehicleOutStorage;


public interface VehicleOutStorageMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(VehicleOutStorage record);

    int insertSelective(VehicleOutStorage record);

    VehicleOutStorage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VehicleOutStorage record);

    int updateByPrimaryKey(VehicleOutStorage record);
    
    List<VehicleOutStorage> findPageByParams(Map<String,Object> params);

}
