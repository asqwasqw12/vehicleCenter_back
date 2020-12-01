package com.eshop.dao;

import java.util.List;
import java.util.Map;

import com.eshop.pojo.VehicleInStorage;

public interface VehicleInStorageMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(VehicleInStorage record);

    int insertSelective(VehicleInStorage record);

    VehicleInStorage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VehicleInStorage record);

    int updateByPrimaryKey(VehicleInStorage record);
    
    List<VehicleInStorage> findPageByParams(Map<String,Object> params);

}
