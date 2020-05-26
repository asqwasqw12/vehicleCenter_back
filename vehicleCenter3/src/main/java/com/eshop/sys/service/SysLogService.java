package com.eshop.sys.service;

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

}
