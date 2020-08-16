package com.eshop.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.common.DateTimeUtils;
import com.eshop.common.FileUtil;
import com.eshop.common.page.MybatisPageHelper;
import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.dao.VehicleMapper;
import com.eshop.pojo.Vehicle;
import com.eshop.service.VehicleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class VehicleServiceImpl implements VehicleService {
	
	@Autowired
	private VehicleMapper vehicleMapper;

	@Override
	public int save(Vehicle record) {
		if(record.getId() == null || record.getId() == 0) {
			return vehicleMapper.insertSelective(record);
		}
		return vehicleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(Vehicle record) {
		return vehicleMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<Vehicle> records) {
		for(Vehicle record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public Vehicle findById(Long id) {
		return vehicleMapper.selectByPrimaryKey(id);
	}

	
	  @Override 
	  public PageResult findPage(PageRequest pageRequest) { 
		  	PageResult pageResult = null;
			Map<String, Object> params = pageRequest.getObjectParam();
			int pageNum = pageRequest.getPageNum();
			int pageSize = pageRequest.getPageSize();
			PageHelper.startPage(pageNum, pageSize);
			List<Vehicle> result = vehicleMapper.findPageByParams(params);
			pageResult = MybatisPageHelper.getPageResult(pageRequest, new PageInfo<Vehicle>((List<Vehicle>) result));
			return pageResult;
	  }
	 
	  @Override
	  public void downloadExcel(List<?> records, HttpServletResponse response) throws IOException{
		  List<Map<String, Object>> list = new ArrayList<>();
	        for (int i = 0; i <records.size(); i++) {
	        	Vehicle vehicle = (Vehicle) records.get(i);
	        	Map<String,Object> map = new LinkedHashMap<>(); 
	        	map.put("ID", vehicle.getId());
	        			 map.put("名称",vehicle.getName());  
	        			 map.put("类型",vehicle.getType()); 
	        			 map.put("车架识别码", vehicle.getVin()); 
	        			 map.put("牌照",vehicle.getLicense()); 
	        			 map.put("所属公司", vehicle.getCompanyId()); 
	        			 map.put("底盘型号", vehicle.getChassis()); 
	        			 map.put("配置", vehicle.getConfigure());
	        			 map.put("创建人",vehicle.getCreateBy()); 
	        			 map.put("创建时间日期",DateTimeUtils.getDateTime(vehicle.getCreateTime())); 
	        			 map.put("最后更新人",vehicle.getLastUpdateBy()); 
	        			 map.put("最后更新时间",DateTimeUtils.getDateTime(vehicle.getLastUpdateTime())); 
	        			 list.add(map);
	        	
	        }
	        FileUtil.downloadExcel(list, response);
	  }

}
