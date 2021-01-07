package com.eshop.gateway.gb32960.service;

import java.util.List;

import com.eshop.gateway.gb32960.pojo.FuelCellData;
import com.eshop.sys.service.CurdService;

public interface FuelCellDataService extends CurdService<FuelCellData>{
	//保存燃料电池单元数据信息
	public int  saveFuelCellData(FuelCellData alarmData);
	
	public List<FuelCellData> findByVehicleId(Long id);
}
