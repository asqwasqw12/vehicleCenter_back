package com.eshop.gateway.gb32960.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.gateway.gb32960.dao.SubSystemTemperatureDataMapper;
import com.eshop.gateway.gb32960.pojo.SubSystemTemperatureData;
import com.eshop.gateway.gb32960.service.SubSystemTemperatureDataService;

@Service
public class SubSystemTemperatureDataServiceImpl implements SubSystemTemperatureDataService{
	@Autowired
	private SubSystemTemperatureDataMapper subSystemTemperatureDataMapper;
	
	@Override
	//保存子系统温度数据信息
	public int  saveSubSystemTemperatureData(SubSystemTemperatureData record) {
		if(record.getId() == null || record.getId() == 0) {
			return subSystemTemperatureDataMapper.insertSelective(record);
		}
		return subSystemTemperatureDataMapper.updateByPrimaryKeySelective(record);
	}
	

	@Override
	public int save(SubSystemTemperatureData record) {
		if(record.getId() == null || record.getId() == 0) {
			return subSystemTemperatureDataMapper.insertSelective(record);
		}
		return subSystemTemperatureDataMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SubSystemTemperatureData record) {
		return subSystemTemperatureDataMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SubSystemTemperatureData> records) {
		for(SubSystemTemperatureData record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SubSystemTemperatureData findById(Long id) {
		return subSystemTemperatureDataMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<SubSystemTemperatureData> findByVehicleId(Long id){
		return subSystemTemperatureDataMapper.findByVehicleId(id);
	}

}
