package com.eshop.jt808.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.eshop.common.RedisUtils;
import com.eshop.common.StringUtils;
import com.eshop.common.page.MybatisPageHelper;
import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.pojo.VehicleLocation;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class LocationInRedisService {
	
	private final static long EXPIRE_TIME = -1;
	private final static String lOCATION_KEY = "real-time-location";
	private RedisUtils redisUtils;
	
	
	public LocationInRedisService(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }
	
	 /**
     * 保存车辆位置信息
     */
    public void save(VehicleLocation vehicleLocation){
    	if(vehicleLocation != null) {
    		redisUtils.set(lOCATION_KEY + vehicleLocation.getVehicleId(), vehicleLocation, EXPIRE_TIME);
    	}
        
    }
    
    /**
     * 查询全部数据
     * @param filter /
     * @param pageable /
     * @return /
     */
    public PageResult findPage(PageRequest pageRequest){
    	String strFilter=null;
    	PageResult pageResult = null;
    	Map<String,Object> params = pageRequest.getObjectParam();
    	if(params.get("filter") != null || params.get("filter") !="") {
    		strFilter = (String) params.get("filter");
    	}
        List<VehicleLocation> vehicleLocationList = getAll(strFilter);
        int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		pageResult = MybatisPageHelper.getPageResult(pageRequest, new PageInfo<VehicleLocation>((List<VehicleLocation>) vehicleLocationList));
        return pageResult;
    }

    /**
     * 查询全部数据，不分页
     * @param filter /
     * @return /
     */
    public List<VehicleLocation> getAll(String filter){
        List<String> keys = redisUtils.scan(lOCATION_KEY + "*");
        Collections.reverse(keys);
        List<VehicleLocation> vehicleLocationList = new ArrayList<>();
        for (String key : keys) {
        	VehicleLocation vehicleLocation = (VehicleLocation) redisUtils.get(key);
            if(StringUtils.isNotBlank(filter)){
                if(vehicleLocation.toString().contains(filter)){
                	vehicleLocationList.add(vehicleLocation);
                }
            } else {
            	vehicleLocationList.add(vehicleLocation);
            }
        }
        vehicleLocationList.sort((o1, o2) -> o2.getTime().compareTo(o1.getTime()));
        return vehicleLocationList;
    }

}
