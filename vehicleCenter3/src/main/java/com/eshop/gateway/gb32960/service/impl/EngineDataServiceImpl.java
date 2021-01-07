package com.eshop.gateway.gb32960.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.gateway.gb32960.dao.EngineDataMapper;
import com.eshop.gateway.gb32960.pojo.EngineData;
import com.eshop.gateway.gb32960.service.EngineDataService;

@Service
public class EngineDataServiceImpl implements EngineDataService{
	@Autowired
	private EngineDataMapper engineDataMapper;
	
	@Override
	//保存引擎数据信息
	public int  saveEngineData(EngineData record) {
		if(record.getId() == null || record.getId() == 0) {
			return engineDataMapper.insertSelective(record);
		}
		return engineDataMapper.updateByPrimaryKeySelective(record);
	}
	

	@Override
	public int save(EngineData record) {
		if(record.getId() == null || record.getId() == 0) {
			return engineDataMapper.insertSelective(record);
		}
		return engineDataMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(EngineData record) {
		return engineDataMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<EngineData> records) {
		for(EngineData record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public EngineData findById(Long id) {
		return engineDataMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<EngineData> findByVehicleId(Long id){
		return engineDataMapper.findByVehicleId(id);
	}
}
