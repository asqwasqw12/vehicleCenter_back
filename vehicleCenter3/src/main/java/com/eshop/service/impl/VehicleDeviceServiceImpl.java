package com.eshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.dao.VehicleDeviceMapper;
import com.eshop.pojo.VehicleDevice;
import com.eshop.service.VehicleDeviceService;

@Service
public class VehicleDeviceServiceImpl implements VehicleDeviceService {
	
	@Autowired
	VehicleDeviceMapper vehicleDeviceMapper;
	
	@Override
	public int save(VehicleDevice record) {
		if(record.getId() == null || record.getId() == 0) {
			return vehicleDeviceMapper.insertSelective(record);
		}
		return vehicleDeviceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(VehicleDevice record) {
		return vehicleDeviceMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<VehicleDevice> records) {
		for(VehicleDevice record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public VehicleDevice findById(Long id) {
		return vehicleDeviceMapper.selectByPrimaryKey(id);
	}
	
	    //根据车辆查询
	    @Override
		public List<VehicleDevice> findByVehicleId(Long vehicleId){
			
			return vehicleDeviceMapper.findByVehicleId(vehicleId);
			
		}
		
		//根据设备号查询
	    @Override
		public VehicleDevice   findByDeviceId(Long deviceId) {
			
			return vehicleDeviceMapper.findByDeviceId(deviceId);
		}
		
		//根据手机号查询
	    @Override
		public VehicleDevice findByTerminalPhone(String terminalPhone) {
			return vehicleDeviceMapper.findByTerminalPhone(terminalPhone);
		}

}
