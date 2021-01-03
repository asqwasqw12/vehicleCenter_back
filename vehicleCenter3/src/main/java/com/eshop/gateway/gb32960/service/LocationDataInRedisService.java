package com.eshop.gateway.gb32960.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.eshop.common.RedisUtils;
import com.eshop.common.StringUtils;
import com.eshop.common.page.MybatisPageHelper;
import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.gateway.gb32960.pojo.LocationData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class LocationDataInRedisService {
	
	private final static long EXPIRE_TIME = -1;
	private final static String lOCATION_KEY = "real_time_location_data";
	private RedisUtils redisUtils;
	
	
	public LocationDataInRedisService(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }
	
	public static String getLocationKey() {
		return lOCATION_KEY;
	}
	
	 /**
     * 保存车辆位置信息
     */
    public void save(LocationData locationData){
    	if(locationData != null) {
    		redisUtils.set(lOCATION_KEY + locationData.getVehicleId(), locationData, EXPIRE_TIME);
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
        List<LocationData> locationDataList = getAll(strFilter);
        int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		pageResult = MybatisPageHelper.getPageResult(pageRequest, new PageInfo<LocationData>((List<LocationData>) locationDataList));
        return pageResult;
    }

    /**
     * 查询全部数据，不分页
     * @param filter /
     * @return /
     */
    public List<LocationData> getAll(String filter){
        List<String> keys = redisUtils.scan(lOCATION_KEY + "*");
        Collections.reverse(keys);
        List<LocationData> locationDataList = new ArrayList<>();
        for (String key : keys) {
        	LocationData locationData = (LocationData) redisUtils.get(key);
            if(StringUtils.isNotBlank(filter)){
                if(locationData.toString().contains(filter)){
                	locationDataList.add(locationData);
                }
            } else {
            	locationDataList.add(locationData);
            }
        }
        locationDataList.sort((o1, o2) -> o2.getSampleTime().compareTo(o1.getSampleTime()));
        return locationDataList;
    }

	

}
