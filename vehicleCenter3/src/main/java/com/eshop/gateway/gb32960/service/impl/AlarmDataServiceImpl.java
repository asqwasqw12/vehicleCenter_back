package com.eshop.gateway.gb32960.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.gateway.gb32960.dao.AlarmDataMapper;
import com.eshop.gateway.gb32960.pojo.AlarmData;
import com.eshop.gateway.gb32960.service.AlarmDataService;

@Service
public class AlarmDataServiceImpl implements AlarmDataService{
	
	@Autowired
	private AlarmDataMapper alarmDataMapper;
	
	@Override
	//保存警报信息
	public int  saveAlarmData(AlarmData record) {
		if(record.getId() == null || record.getId() == 0) {
			return alarmDataMapper.insertSelective(record);
		}
		return alarmDataMapper.updateByPrimaryKeySelective(record);
	}
	

	@Override
	public int save(AlarmData record) {
		if(record.getId() == null || record.getId() == 0) {
			return alarmDataMapper.insertSelective(record);
		}
		return alarmDataMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(AlarmData record) {
		return alarmDataMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<AlarmData> records) {
		for(AlarmData record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public AlarmData findById(Long id) {
		return alarmDataMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<AlarmData> findByVehicleId(Long id){
		return alarmDataMapper.findByVehicleId(id);
	}

}
