package com.eshop.gateway.gb32960.dao;

import java.util.List;

import com.eshop.gateway.gb32960.pojo.FuelCellData;



public interface FuelCellDataMapper {

	int deleteByPrimaryKey(Long id);

    int insert(FuelCellData record);

    int insertSelective(FuelCellData record);

    FuelCellData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FuelCellData record);

    int updateByPrimaryKey(FuelCellData record);
    
    List<FuelCellData> findByVehicleId(Long id); 
    
    List<FuelCellData> findAll();
}
