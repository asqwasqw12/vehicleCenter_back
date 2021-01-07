package com.eshop.gateway.gb32960.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.gateway.gb32960.dao.ExtremeDataMapper;
import com.eshop.gateway.gb32960.pojo.ExtremeData;
import com.eshop.gateway.gb32960.service.ExtremeDataService;

@Service
public class ExtremeDataServiceImpl implements ExtremeDataService {
	@Autowired
	private ExtremeDataMapper extremeDataMapper;
	
	@Override
	//保存极值数据信息
	public int  saveExtremeData(ExtremeData record) {
		if(record.getId() == null || record.getId() == 0) {
			return extremeDataMapper.insertSelective(record);
		}
		return extremeDataMapper.updateByPrimaryKeySelective(record);
	}
	

	@Override
	public int save(ExtremeData record) {
		if(record.getId() == null || record.getId() == 0) {
			return extremeDataMapper.insertSelective(record);
		}
		return extremeDataMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(ExtremeData record) {
		return extremeDataMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<ExtremeData> records) {
		for(ExtremeData record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public ExtremeData findById(Long id) {
		return extremeDataMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<ExtremeData> findByVehicleId(Long id){
		return extremeDataMapper.findByVehicleId(id);
	}
}
