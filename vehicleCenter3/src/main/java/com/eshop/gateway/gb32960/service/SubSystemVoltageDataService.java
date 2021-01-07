package com.eshop.gateway.gb32960.service;

import java.util.List;

import com.eshop.gateway.gb32960.pojo.SubSystemVoltageData;
import com.eshop.sys.service.CurdService;

public interface SubSystemVoltageDataService extends CurdService<SubSystemVoltageData>{
	//保存子系统电压数据信息
	public int  saveSubSystemVoltageData(SubSystemVoltageData subSystemVoltageData);
	
	public List<SubSystemVoltageData> findByVehicleId(Long id);
}

