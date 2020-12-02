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
import com.eshop.dao.VehicleInStorageMapper;
import com.eshop.pojo.Vehicle;
import com.eshop.pojo.VehicleInStorage;
import com.eshop.service.VehicleInStorageService;
import com.eshop.service.VehicleService;
import com.eshop.sys.pojo.SysUser;
import com.eshop.sys.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class VehicleInStorageServiceImpl implements VehicleInStorageService {
	@Autowired 
	private VehicleInStorageMapper vehicleInStroageMapper;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private SysUserService sysUserService;
	@Override
	public int save(VehicleInStorage record) {
		if(record.getId() == null || record.getId() == 0) {
			return vehicleInStroageMapper.insertSelective(record);
		}
		return vehicleInStroageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(VehicleInStorage record) {
		return vehicleInStroageMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<VehicleInStorage> records) {
		for(VehicleInStorage record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public VehicleInStorage findById(Long id) {
		return vehicleInStroageMapper.selectByPrimaryKey(id);
	}
	
	@Override 
	  public PageResult findPage(PageRequest pageRequest) { 
		  	PageResult pageResult = null;
			Map<String, Object> params = pageRequest.getObjectParam();
			int pageNum = pageRequest.getPageNum();
			int pageSize = pageRequest.getPageSize();
			PageHelper.startPage(pageNum, pageSize);
			List<VehicleInStorage> result =  vehicleInStroageMapper.findPageByParams(params);
			if(result!=null) {
				findVehicleProperty(result);
				findOperatorName(result);				
			}
			pageResult = MybatisPageHelper.getPageResult(pageRequest, new PageInfo<VehicleInStorage>((List<VehicleInStorage>) result));
			return pageResult;
	  }
	
	//获取车辆属性
	private void findVehicleProperty(List<VehicleInStorage> list) {
		  for(VehicleInStorage vehicleInStorage : list) {
		  if(vehicleInStorage.getVehicleId()!= null) {
			  Vehicle vehicle = vehicleService.findById(vehicleInStorage.getVehicleId());
				if(vehicle!=null) {			
					vehicleInStorage.setName(vehicle.getName());
					vehicleInStorage.setType(vehicle.getType());
					vehicleInStorage.setVin(vehicle.getVin());
					vehicleInStorage.setCompanyNum(vehicle.getCompanyNum());
				}
			}
	  }
	}
		  
	//获取经办人姓名
    private void findOperatorName(List<VehicleInStorage> list) {
    	for(VehicleInStorage vehicleInStorage :list) {
    		if(vehicleInStorage.getUserId()!= null) {
    			SysUser sysUser = sysUserService.findById(vehicleInStorage.getUserId());
    			if(sysUser != null) {
    				vehicleInStorage.setOperator(sysUser.getName());
    			}
    		}
    	}
    }
    
    @Override
	  public void downloadExcel(List<?> records, HttpServletResponse response) throws IOException{
		  List<Map<String, Object>> list = new ArrayList<>();
	        for (int i = 0; i <records.size(); i++) {
	        	VehicleInStorage vehicleInStorage = (VehicleInStorage) records.get(i);
	        	Map<String,Object> map = new LinkedHashMap<>(); 
	        	map.put("ID", vehicleInStorage.getId());
	        	map.put("车辆编号",vehicleInStorage.getVehicleId());  
	        			 map.put("名称",vehicleInStorage.getName());  
	        			 map.put("类型",vehicleInStorage.getType()); 
	        			 map.put("车架识别码", vehicleInStorage.getVin()); 
	        			 map.put("出厂编号", vehicleInStorage.getCompanyNum()); 
	        			 map.put("经办人id", vehicleInStorage.getUserId()); 
	        			 map.put("经办人", vehicleInStorage.getOperator());
	        			 map.put("入库时间", vehicleInStorage.getInTime());
	        			 map.put("库房名称", vehicleInStorage.getStorageName());
	        			 map.put("创建时间日期",DateTimeUtils.getDateTime(vehicleInStorage.getCreateTime())); 
	        			 map.put("最后更新人",vehicleInStorage.getLastUpdateBy()); 
	        			 map.put("最后更新时间",DateTimeUtils.getDateTime(vehicleInStorage.getLastUpdateTime())); 
	        			 list.add(map);
	        	
	        }
	        FileUtil.downloadExcel(list, response);
	  }
		  
}
