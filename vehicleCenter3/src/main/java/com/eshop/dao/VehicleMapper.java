package com.eshop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.eshop.pojo.Vehicle;

public interface VehicleMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(Vehicle record);

    int insertSelective(Vehicle record);

    Vehicle selectByPrimaryKey(Long id);
    
   // Vehicle findByVinAndIccid(@Param("vin")String vin,@Param("iccid")String iccid);
    
    Vehicle findByVin(@Param("vin")String vin);
    
    Vehicle findByTerminalNum(@Param("termnialNum")String terminalNum);

    int updateByPrimaryKeySelective(Vehicle record);

    int updateByPrimaryKey(Vehicle record);
    
    List<Vehicle> findPageByParams(Map<String,Object> params);
    
    //查询所有车辆名称
  	List<String> findAllNames();
  	
    //查询车辆型号
  	List<String> findType(String name);

}
