package com.eshop.service;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.pojo.VehicleOutStorage;
import com.eshop.sys.service.CurdService;

public interface VehicleOutStorageService extends CurdService<VehicleOutStorage>{
	
	//根据参数查询
	 PageResult findPage(PageRequest pageRequest);
		 
	//下载excel文件
	void downloadExcel(List<?> records, HttpServletResponse response) throws IOException;
	
	int save(List<VehicleOutStorage> records);

}

