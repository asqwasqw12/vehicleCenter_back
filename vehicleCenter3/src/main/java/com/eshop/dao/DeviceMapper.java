package com.eshop.dao;

import java.util.List;
import java.util.Map;

import com.eshop.pojo.Device;


public interface DeviceMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);
    
    List<Device> findPageByParams(Map<String,Object> params);

}
