package com.eshop.jt808.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eshop.jt808.pojo.Location;


public interface LocationMapper {
	
	 	int deleteByPrimaryKey(Long id);

	    int insert(Location record);

	    int insertSelective(Location record);

	    Location selectByPrimaryKey(Long id);

	    int updateByPrimaryKeySelective(Location record);

	    int updateByPrimaryKey(Location record);
	    
	    List<Location> findByVehicleId(Long id); 
	    
	    
	    
	    List<Location> findAll();

}
