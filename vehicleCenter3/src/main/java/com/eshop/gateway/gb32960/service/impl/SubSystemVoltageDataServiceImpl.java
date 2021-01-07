package com.eshop.gateway.gb32960.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.eshop.gateway.gb32960.dao.SubSystemVoltageDataMapper;
import com.eshop.gateway.gb32960.pojo.SubSystemVoltageData;
import com.eshop.gateway.gb32960.service.SubSystemVoltageDataService;

public class SubSystemVoltageDataServiceImpl implements SubSystemVoltageDataService{
	@Autowired
	private SubSystemVoltageDataMapper SubSystemVoltageDataMapper;
	
	@Override
	//保存子系统电压信息
	public int  saveSubSystemVoltageData(SubSystemVoltageData record) {
		if(record.getId() == null || record.getId() == 0) {
			return SubSystemVoltageDataMapper.insertSelective(record);
		}
		return SubSystemVoltageDataMapper.updateByPrimaryKeySelective(record);
	}
	

	@Override
	public int save(SubSystemVoltageData record) {
		if(record.getId() == null || record.getId() == 0) {
			return SubSystemVoltageDataMapper.insertSelective(record);
		}
		return SubSystemVoltageDataMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SubSystemVoltageData record) {
		return SubSystemVoltageDataMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SubSystemVoltageData> records) {
		for(SubSystemVoltageData record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SubSystemVoltageData findById(Long id) {
		return SubSystemVoltageDataMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<SubSystemVoltageData> findByVehicleId(Long id){
		return SubSystemVoltageDataMapper.findByVehicleId(id);
	}
}
