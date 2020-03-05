package com.eshop.jt808.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.jt808.dao.LocationDao;
import com.eshop.jt808.pojo.Location;
import com.eshop.jt808.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationDao locationDao;
	
	@Override
	//保存位置信息
	public int  saveLocation(Location location) {
	  return locationDao.saveLocation(location);
	}

}
