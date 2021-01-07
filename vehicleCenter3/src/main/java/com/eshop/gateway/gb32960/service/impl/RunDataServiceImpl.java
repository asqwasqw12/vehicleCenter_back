package com.eshop.gateway.gb32960.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.gateway.gb32960.dao.RunDataMapper;
import com.eshop.gateway.gb32960.pojo.RunData;
import com.eshop.gateway.gb32960.service.RunDataService;

@Service
public class RunDataServiceImpl implements RunDataService{
	@Autowired
	private RunDataMapper runDataMapper;
	
	@Override
	//保存运行数据信息
	public int  saveRunData(RunData record) {
		if(record.getId() == null || record.getId() == 0) {
			return runDataMapper.insertSelective(record);
		}
		return runDataMapper.updateByPrimaryKeySelective(record);
	}
	

	@Override
	public int save(RunData record) {
		if(record.getId() == null || record.getId() == 0) {
			return runDataMapper.insertSelective(record);
		}
		return runDataMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(RunData record) {
		return runDataMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<RunData> records) {
		for(RunData record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public RunData findById(Long id) {
		return runDataMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<RunData> findByVehicleId(Long id){
		return runDataMapper.findByVehicleId(id);
	}

}
