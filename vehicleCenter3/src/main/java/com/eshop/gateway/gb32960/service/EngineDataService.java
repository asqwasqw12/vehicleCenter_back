package com.eshop.gateway.gb32960.service;

import java.util.List;

import com.eshop.gateway.gb32960.pojo.EngineData;
import com.eshop.sys.service.CurdService;

public interface EngineDataService extends CurdService<EngineData>{
	//保存引擎信息
	public int  saveEngineData(EngineData engineData);
	
	public List<EngineData> findByVehicleId(Long id);
}
