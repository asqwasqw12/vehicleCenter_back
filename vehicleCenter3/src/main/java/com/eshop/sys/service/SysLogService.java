package com.eshop.sys.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.sys.pojo.SysLog;

/**
 * 操作日志
 * @author Louis
 * @date Jan 13, 2019
 */
public interface SysLogService extends CurdService<SysLog> {
	
	//根据参数查询
	PageResult findPage(PageRequest pageRequest);
	
	public int delete(Map<String,Object> params);
	
	void downloadExcel(List<?> records, HttpServletResponse response) throws IOException;

}
