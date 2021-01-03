package com.eshop.gateway.gb32960.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.gateway.gb32960.dao.LocationDataMapper;
import com.eshop.gateway.gb32960.pojo.LocationData;
import com.eshop.gateway.gb32960.service.LocationDataService;

@Service
public class LocationDataServiceImpl implements LocationDataService{
	
	@Autowired
	private LocationDataMapper locationDataMapper;
	
	@Override
	//保存位置信息
	public int  saveLocationData(LocationData record) {
		if(record.getId() == null || record.getId() == 0) {
			return locationDataMapper.insertSelective(record);
		}
		return locationDataMapper.updateByPrimaryKeySelective(record);
	}
	

	@Override
	public int save(LocationData record) {
		if(record.getId() == null || record.getId() == 0) {
			return locationDataMapper.insertSelective(record);
		}
		return locationDataMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(LocationData record) {
		return locationDataMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<LocationData> records) {
		for(LocationData record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public LocationData findById(Long id) {
		return locationDataMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<LocationData> findByVehicleId(Long id){
		return locationDataMapper.findByVehicleId(id);
	}

}
