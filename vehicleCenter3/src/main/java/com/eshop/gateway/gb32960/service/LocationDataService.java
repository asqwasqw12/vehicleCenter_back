package com.eshop.gateway.gb32960.service;


import java.util.List;
import com.eshop.gateway.gb32960.pojo.LocationData;
import com.eshop.sys.service.CurdService;


public interface LocationDataService extends CurdService<LocationData >{
	
	   //保存位置信息
		public int  saveLocationData(LocationData locationData);
		
		public List<LocationData> findByVehicleId(Long id);

}
