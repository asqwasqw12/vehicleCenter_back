package com.eshop.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.common.RedisUtils;
import com.eshop.common.StringUtils;
import com.eshop.gateway.gb32960.pojo.LocationData;
import com.eshop.gateway.gb32960.service.LocationDataInRedisService;
import com.eshop.jt808.pojo.Location;
import com.eshop.jt808.service.LocationInRedisService;
import com.eshop.pojo.Vehicle;
import com.eshop.pojo.VehicleStatus;

@Service
public class VehicleStatusInRedisService {
	
    private RedisUtils redisUtils;
    @Autowired
    VehicleService  vehicleService;
	
	
	public  VehicleStatusInRedisService(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }
	
	 /**
     * 查询全部数据，不分页
     * @param filter /
     * @return /
     */
    public List<VehicleStatus> getAll(String filter){
        //List<String> keys = redisUtils.scan(LocationInRedisService.getLocationKey() + "*");
        List<String> keys = redisUtils.scan(LocationDataInRedisService.getLocationKey() + "*");
        Collections.reverse(keys);
        List<VehicleStatus> vehicleStatusList = new ArrayList<>();
        for (String key : keys) {
        	LocationData locationData = (LocationData) redisUtils.get(key);
        	Vehicle vehicle = vehicleService.findById(locationData.getVehicleId());
        	VehicleStatus vehicleStatus = new VehicleStatus();
        	vehicleStatus.setVehicleId(locationData.getVehicleId());
        	vehicleStatus.setLocationData(locationData);
        	if(vehicle !=null) {
        		vehicleStatus.setVehicle(vehicle);
        	}
            if(StringUtils.isNotBlank(filter)){
                if(vehicleStatus.toString().contains(filter)){
                	vehicleStatusList.add(vehicleStatus);
                }
            } else {
            	vehicleStatusList.add(vehicleStatus);
            }
        }
        return vehicleStatusList;
    }


}
