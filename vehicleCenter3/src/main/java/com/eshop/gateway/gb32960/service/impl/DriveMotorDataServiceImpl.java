package com.eshop.gateway.gb32960.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.gateway.gb32960.dao.DriveMotorDataMapper;
import com.eshop.gateway.gb32960.pojo.DriveMotorData;
import com.eshop.gateway.gb32960.service.DriveMotorDataService;

@Service
public class DriveMotorDataServiceImpl implements DriveMotorDataService{
	@Autowired
	private DriveMotorDataMapper driveMotorDataMapper;
	
	@Override
	//保存驱动电机信息
	public int  saveDriveMotorData(DriveMotorData record) {
		if(record.getId() == null || record.getId() == 0) {
			return driveMotorDataMapper.insertSelective(record);
		}
		return driveMotorDataMapper.updateByPrimaryKeySelective(record);
	}
	

	@Override
	public int save(DriveMotorData record) {
		if(record.getId() == null || record.getId() == 0) {
			return driveMotorDataMapper.insertSelective(record);
		}
		return driveMotorDataMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(DriveMotorData record) {
		return driveMotorDataMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<DriveMotorData> records) {
		for(DriveMotorData record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public DriveMotorData findById(Long id) {
		return driveMotorDataMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<DriveMotorData> findByVehicleId(Long id){
		return driveMotorDataMapper.findByVehicleId(id);
	}

}
