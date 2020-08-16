package com.eshop.jt808.service;

import java.util.List;

import com.eshop.jt808.pojo.Location;
import com.eshop.sys.service.CurdService;


public interface LocationService extends CurdService<Location >{
	//保存位置信息
	public int  saveLocation(Location location);
	
	public List<Location> findByVehicleId(Long id);

}
