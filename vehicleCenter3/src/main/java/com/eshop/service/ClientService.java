package com.eshop.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.pojo.Client;
import com.eshop.sys.service.CurdService;

public interface ClientService extends CurdService<Client> {
	
	//根据参数查询
	 PageResult findPage(PageRequest pageRequest);
	 
	//下载excel文件
	void downloadExcel(List<?> records, HttpServletResponse response) throws IOException;
	
	//根据公司id查询
	 List<Client> findByCompanyId(Long companyId);

}
