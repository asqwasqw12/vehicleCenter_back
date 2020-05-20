package com.eshop.sys.service;

import java.util.List;

import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.sys.pojo.SysDict;

public interface SysDictService extends CurdService<SysDict>{
	
	/**
	 * 根据名称查询
	 * @param lable
	 * @return
	 */
	//List<SysDict> findByLable(String lable);
	
	//根据参数查询
	 PageResult findPage(PageRequest pageRequest);

}
