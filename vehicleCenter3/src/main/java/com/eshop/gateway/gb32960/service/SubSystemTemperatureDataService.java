package com.eshop.gateway.gb32960.service;

import java.util.List;

import com.eshop.gateway.gb32960.pojo.SubSystemTemperatureData;
import com.eshop.sys.service.CurdService;

public interface SubSystemTemperatureDataService extends CurdService<SubSystemTemperatureData>{
	//保存子系统温度数据信息
	public int  saveSubSystemTemperatureData(SubSystemTemperatureData subSystemTemperatureData);
	
	public List<SubSystemTemperatureData> findByVehicleId(Long id);
}

