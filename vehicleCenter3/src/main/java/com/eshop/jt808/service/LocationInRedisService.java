package com.eshop.jt808.service;

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
import com.eshop.jt808.pojo.Location;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class LocationInRedisService {
	
	private final static long EXPIRE_TIME = -1;
	private final static String lOCATION_KEY = "real_time_location";
	private RedisUtils redisUtils;
	
	
	public LocationInRedisService(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }
	
	public static String getLocationKey() {
		return lOCATION_KEY;
	}
	
	 /**
     * 保存车辆位置信息
     */
    public void save(Location location){
    	if(location != null) {
    		redisUtils.set(lOCATION_KEY + location.getVehicleId(), location, EXPIRE_TIME);
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
        List<Location> locationList = getAll(strFilter);
        int pageNum = pageRequest.getPageNum();
		int pageSize = pageRequest.getPageSize();
		PageHelper.startPage(pageNum, pageSize);
		pageResult = MybatisPageHelper.getPageResult(pageRequest, new PageInfo<Location>((List<Location>) locationList));
        return pageResult;
    }

    /**
     * 查询全部数据，不分页
     * @param filter /
     * @return /
     */
    public List<Location> getAll(String filter){
        List<String> keys = redisUtils.scan(lOCATION_KEY + "*");
        Collections.reverse(keys);
        List<Location> locationList = new ArrayList<>();
        for (String key : keys) {
        	Location location = (Location) redisUtils.get(key);
            if(StringUtils.isNotBlank(filter)){
                if(location.toString().contains(filter)){
                	locationList.add(location);
                }
            } else {
            	locationList.add(location);
            }
        }
        locationList.sort((o1, o2) -> o2.getTime().compareTo(o1.getTime()));
        return locationList;
    }

}
