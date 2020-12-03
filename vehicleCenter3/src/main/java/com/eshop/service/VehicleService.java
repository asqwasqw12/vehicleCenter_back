package com.eshop.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.pojo.Vehicle;
import com.eshop.sys.service.CurdService;

public interface VehicleService extends CurdService<Vehicle>{
	
	//根据参数查询
	 PageResult findPage(PageRequest pageRequest);
	 
	//下载excel文件
	void downloadExcel(List<?> records, HttpServletResponse response) throws IOException;
	
	//查询所有车辆名称
  	List<String> findAllNames();
  	
    //查询车辆型号
  	List<String> findType(String name);
  	
  	//查询车辆名称和型号
  	List<Map<String,Object>> findNamesAndType();

}
