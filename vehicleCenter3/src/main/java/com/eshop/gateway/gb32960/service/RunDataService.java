package com.eshop.gateway.gb32960.service;

import java.util.List;

import com.eshop.gateway.gb32960.pojo.RunData;
import com.eshop.sys.service.CurdService;

public interface RunDataService extends CurdService<RunData>{
	//保存车辆运行数据信息
	public int  saveRunData(RunData runData);
	
	public List<RunData> findByVehicleId(Long id);
}
