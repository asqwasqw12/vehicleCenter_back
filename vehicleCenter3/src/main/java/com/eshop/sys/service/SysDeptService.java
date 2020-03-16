package com.eshop.sys.service;

import java.util.List;

import com.eshop.sys.pojo.SysDept;

public interface SysDeptService extends CurdService<SysDept>{
	
	/**
	 * 查询机构树
	 * @param userId 
	 * @return
	 */
	List<SysDept> findTree();

}
