package com.eshop.gateway.gb32960.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.gateway.gb32960.dao.FuelCellDataMapper;
import com.eshop.gateway.gb32960.pojo.FuelCellData;
import com.eshop.gateway.gb32960.service.FuelCellDataService;

@Service
public class FuelCellDataServiceImpl implements FuelCellDataService{
	
	@Autowired
	private FuelCellDataMapper fuelCellDataMapper;
	
	@Override
	//保存警报信息
	public int  saveFuelCellData(FuelCellData record) {
		if(record.getId() == null || record.getId() == 0) {
			return fuelCellDataMapper.insertSelective(record);
		}
		return fuelCellDataMapper.updateByPrimaryKeySelective(record);
	}
	

	@Override
	public int save(FuelCellData record) {
		if(record.getId() == null || record.getId() == 0) {
			return fuelCellDataMapper.insertSelective(record);
		}
		return fuelCellDataMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(FuelCellData record) {
		return fuelCellDataMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<FuelCellData> records) {
		for(FuelCellData record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public FuelCellData findById(Long id) {
		return fuelCellDataMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<FuelCellData> findByVehicleId(Long id){
		return fuelCellDataMapper.findByVehicleId(id);
	}

}
