package com.eshop.gateway.gb32960.service;

import java.util.List;

import com.eshop.gateway.gb32960.pojo.AlarmData;
import com.eshop.sys.service.CurdService;

public interface AlarmDataService extends CurdService<AlarmData>{
	
	        //保存警报信息
			public int  saveAlarmData(AlarmData alarmData);
			
			public List<AlarmData> findByVehicleId(Long id);


}
