package com.eshop.jt808.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.jt808.dao.LocationDao;
import com.eshop.jt808.dao.LocationMapper;
import com.eshop.jt808.pojo.Location;
import com.eshop.jt808.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationDao locationDao;
	@Autowired
	private LocationMapper locationMapper;
	
	@Override
	//保存位置信息
	public int  saveLocation(Location location) {
	  return locationDao.saveLocation(location);
	}
	

	@Override
	public int save(Location record) {
		if(record.getId() == null || record.getId() == 0) {
			return locationMapper.insertSelective(record);
		}
		return locationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(Location record) {
		return locationMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<Location> records) {
		for(Location record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public Location findById(Long id) {
		return locationMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Location> findByVehicleId(Long id){
		return locationMapper.findByVehicleId(id);
	}


}
