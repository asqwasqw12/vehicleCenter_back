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
import com.eshop.sys.service.SysDeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class VehicleServiceImpl implements VehicleService {
	
	@Autowired
	private VehicleMapper vehicleMapper;
	@Autowired
	private SysDeptService  sysDeptService;

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
	
	/*
	 * @Override public Vehicle findByVinAndIccid(String vin,String iccid) { return
	 * vehicleMapper.findByVinAndIccid(vin,iccid); }
	 */
	
	//查询车辆
	@Override
  	public Vehicle findByVin(String vin) {
  		return vehicleMapper.findByVin(vin);
  	}


	@Override
	public List<String> findAllNames(){
		return vehicleMapper.findAllNames();
	}
	
	@Override
  	public List<String> findType(String name){
		return vehicleMapper.findType(name);
	}
	
	@Override
  	public List<Map<String,Object>> findNamesAndType(){
		List<String> listNames = new ArrayList<>(); //车辆名称列表
		List<String> listType = new ArrayList<>(); //车辆名称列表
		List<Map<String,Object>> list = new ArrayList<>();  //车辆名称型号列表
		listNames= vehicleMapper.findAllNames();
		for(String name : listNames) {
			Map<String,Object> map = new LinkedHashMap<>(); 
			if (name != null) {
				listType= vehicleMapper.findType(name);
				if (listType != null) {
					map.put("name", name);
					map.put("type", listType);
					list.add(map);
				}
			}
		}
		return list;
	}
	
	  @Override 
	  public PageResult findPage(PageRequest pageRequest) { 
		  	PageResult pageResult = null;
			Map<String, Object> params = pageRequest.getObjectParam();
			int pageNum = pageRequest.getPageNum();
			int pageSize = pageRequest.getPageSize();
			PageHelper.startPage(pageNum, pageSize);
			List<Vehicle> result = vehicleMapper.findPageByParams(params);
			if(result!=null) {
				findOwnerCompanyName(result);
				findUserCompanyName(result);
								
			}
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
	        			 map.put("品牌",vehicle.getBrand()); 
	        			 map.put("车架识别码", vehicle.getVin()); 
	        			 map.put("牌照",vehicle.getLicense()); 
	        			 map.put("出厂编号", vehicle.getCompanyNum()); 
	        			 map.put("底盘型号", vehicle.getChassis()); 
	        			 map.put("配置", vehicle.getConfigure());
	        			 map.put("所属机构Id", vehicle.getOwnerCompanyId());
	        			 map.put("所属机构名称", vehicle.getOwnerCompanyName());
	        			 map.put("使用机构名称", vehicle.getUserCompanyName());
	        			 map.put("使用机构", vehicle.getUserCompanyId());
	        			 map.put("资产状态", vehicle.getStatus());
	        			 map.put("生产状态", vehicle.getManufactureStatus());
	        			 map.put("购买方式", vehicle.getPurchaseWay());
	        			 map.put("配置", vehicle.getConfigure());
	        			 map.put("创建人",vehicle.getCreateBy()); 
	        			 map.put("创建时间日期",DateTimeUtils.getDateTime(vehicle.getCreateTime())); 
	        			 map.put("最后更新人",vehicle.getLastUpdateBy()); 
	        			 map.put("最后更新时间",DateTimeUtils.getDateTime(vehicle.getLastUpdateTime())); 
	        			 list.add(map);
	        	
	        }
	        FileUtil.downloadExcel(list, response);
	  }
	  
	  private void findOwnerCompanyName(List<Vehicle> list) {
		  for(Vehicle vehicle : list) {
				if(vehicle.getOwnerCompanyId()!= null) {
					if(sysDeptService.findById(vehicle.getOwnerCompanyId())!=null) {
						vehicle.setOwnerCompanyName(sysDeptService.findById(vehicle.getOwnerCompanyId()).getName());
					}
				}
	  }
 }

	  private void findUserCompanyName(List<Vehicle> list) {
		  for(Vehicle vehicle : list) {
		  if(vehicle.getUserCompanyId()!= null) {
				if(sysDeptService.findById(vehicle.getUserCompanyId())!=null) {
					vehicle.setUserCompanyName(sysDeptService.findById(vehicle.getUserCompanyId()).getName());
				}
			}
	  }
  }
}
