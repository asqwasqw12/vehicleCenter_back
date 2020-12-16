package com.eshop.service.impl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.common.DateTimeUtils;
import com.eshop.common.FileUtil;
import com.eshop.common.page.MybatisPageHelper;
import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.dao.VehicleOutStorageMapper;
import com.eshop.pojo.Client;
import com.eshop.pojo.Company;
import com.eshop.pojo.Vehicle;
import com.eshop.pojo.VehicleOutStorage;
import com.eshop.service.ClientService;
import com.eshop.service.CompanyService;
import com.eshop.service.VehicleOutStorageService;
import com.eshop.service.VehicleService;
import com.eshop.sys.pojo.SysUser;
import com.eshop.sys.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class VehicleOutStorageServiceImpl implements VehicleOutStorageService{
	
	@Autowired 
	private VehicleOutStorageMapper vehicleOutStroageMapper;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Override
	@Transactional
	public int save(VehicleOutStorage record) {
		if(record.getId() == null || record.getId() == 0) {
			Vehicle vehicle = vehicleService.findById(record.getVehicleId());
			if (vehicle !=null) {
				vehicle.setManufactureStatus((byte) 2);
			}
			vehicleService.save(vehicle);//在保存出库信息之前，将车辆制造状态修改为出库状态
			return vehicleOutStroageMapper.insertSelective(record);
		}
		return vehicleOutStroageMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public int save(List<VehicleOutStorage> records) {
		if(records == null || records.isEmpty()) {
			return 1;
		}
		for(VehicleOutStorage record:records) {
			save(record);
			//vehicleInStroageMapper.insertSelective(record);
		}
		return 1;
	}

	@Override
	public int delete(VehicleOutStorage record) {
		return vehicleOutStroageMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<VehicleOutStorage> records) {
		for(VehicleOutStorage record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public VehicleOutStorage findById(Long id) {
		return vehicleOutStroageMapper.selectByPrimaryKey(id);
	}
	
	@Override 
	  public PageResult findPage(PageRequest pageRequest) { 
		  	PageResult pageResult = null;
			//Map<String, Object> params = pageRequest.getObjectParam();
			Map<String, Object> params = handlePageRequest(pageRequest);
			int pageNum = pageRequest.getPageNum();
			int pageSize = pageRequest.getPageSize();
			PageHelper.startPage(pageNum, pageSize);
			List<VehicleOutStorage> result =  vehicleOutStroageMapper.findPageByParams(params);
			if(result!=null) {
				findVehicleProperty(result);
				findClientProperty(result);
				findCompanyProperty(result);
				findOperatorName(result);				
			}
			pageResult = MybatisPageHelper.getPageResult(pageRequest, new PageInfo<VehicleOutStorage>((List<VehicleOutStorage>) result));
			return pageResult;
	  }
	
	
	// PageRequest参数处理函数
		private Map<String, Object> handlePageRequest(PageRequest pageRequest) {
			Map<String, Object> params = new HashMap<>();
			params = pageRequest.getObjectParam();

			// 处理注册时间参数
			if (params.get("outTime") != null && params.get("outTime") != "") {
				List<String> strList = new ArrayList<>();
				strList = (ArrayList<String>) params.get("outTime");
				if (strList.size() > 0) {
					String startTime = strList.get(0);
					String endTime = strList.get(1);
					params.put("startTime", startTime);
					params.put("endTime", endTime);
				}
			}
			return params;
		}
	//获取车辆信息
	private void findVehicleProperty(List<VehicleOutStorage> list) {
		  for(VehicleOutStorage vehicleOutStorage : list) {
		  if(vehicleOutStorage.getVehicleId()!= null) {
			  Vehicle vehicle = vehicleService.findById(vehicleOutStorage.getVehicleId());
				if(vehicle!=null) {			
					vehicleOutStorage.setVehicle(vehicle);
				}
			}
	  }
	}
	
	//获取客户信息
	private void findClientProperty(List<VehicleOutStorage> list) {
		  for(VehicleOutStorage vehicleOutStorage : list) {
		  if(vehicleOutStorage.getClientId()!= null) {
			  Client client = clientService.findById(vehicleOutStorage.getClientId());
				if(client!=null) {			
					vehicleOutStorage.setClient(client);
				}
			}
	  }
	}
	
	//获取客户公司信息
	private void findCompanyProperty(List<VehicleOutStorage> list) {
		  for(VehicleOutStorage vehicleOutStorage : list) {
		  if(vehicleOutStorage.getCompanyId()!= null) {
			  Company company = companyService.findById(vehicleOutStorage.getCompanyId());
				if(company!=null) {			
					vehicleOutStorage.setCompany(company);
				}
			}
	  }
	}
		  
	//获取经办人姓名
    private void findOperatorName(List<VehicleOutStorage> list) {
    	for(VehicleOutStorage vehicleOutStorage :list) {
    		if(vehicleOutStorage.getUserId()!= null) {
    			SysUser sysUser = sysUserService.findById(vehicleOutStorage.getUserId());
    			if(sysUser != null) {
    				vehicleOutStorage.setOperator(sysUser.getRealName());
    			}
    		}
    	}
    }
    
    @Override
	  public void downloadExcel(List<?> records, HttpServletResponse response) throws IOException{
		  List<Map<String, Object>> list = new ArrayList<>();
	        for (int i = 0; i <records.size(); i++) {
	        	VehicleOutStorage vehicleOutStorage = (VehicleOutStorage) records.get(i);
	        	Map<String,Object> map = new LinkedHashMap<>(); 
	        	map.put("ID", vehicleOutStorage.getId());
	        	map.put("车辆编号",vehicleOutStorage.getVehicleId());    
	        			 map.put("经办人ID",vehicleOutStorage.getUserId()); 
	        			 map.put("经办人",vehicleOutStorage.getOperator());
	        			 map.put("车辆名称", vehicleOutStorage.getVehicle().getName());
	        			 map.put("车辆型号", vehicleOutStorage.getVehicle().getType());
	        			 map.put("车架识别码", vehicleOutStorage.getVehicle().getVin()); 
	        			 map.put("出厂编号", vehicleOutStorage.getVehicle().getCompanyNum()); 
	        			 map.put("出库时间", vehicleOutStorage.getOutTime());
	        			 map.put("出库原因", vehicleOutStorage.getReason());
	        			 map.put("目的地", vehicleOutStorage.getDestination());
	        			 map.put("客户ID", vehicleOutStorage.getClientId());
	        			 map.put("客户姓名", vehicleOutStorage.getClient().getName());
	        			 map.put("客户公司ID", vehicleOutStorage.getCompanyId());
	        			 map.put("客户公司名称", vehicleOutStorage.getCompany().getName());
	        			 map.put("创建时间日期",DateTimeUtils.getDateTime(vehicleOutStorage.getCreateTime())); 
	        			 map.put("最后更新人",vehicleOutStorage.getLastUpdateBy()); 
	        			 map.put("最后更新时间",DateTimeUtils.getDateTime(vehicleOutStorage.getLastUpdateTime())); 
	        			 list.add(map);
	        	
	        }
	        FileUtil.downloadExcel(list, response);
	  }

}
