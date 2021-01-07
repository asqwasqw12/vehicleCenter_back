package com.eshop.gateway.gb32960.service;

import java.util.List;

import com.eshop.gateway.gb32960.pojo.ExtremeData;
import com.eshop.sys.service.CurdService;

public interface ExtremeDataService extends CurdService<ExtremeData>{
	//保存极值数据信息
	public int  saveExtremeData(ExtremeData extremeData);
	
	public List<ExtremeData> findByVehicleId(Long id);
}
