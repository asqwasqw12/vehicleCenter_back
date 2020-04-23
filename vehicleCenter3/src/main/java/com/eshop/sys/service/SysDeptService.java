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
	
	//获取所有子Id
	List<Long> getDeptChildren(Long id);
	
	//获取所有子Id
	List<Long> getDeptChildren(List<SysDept> deptList);
	
	
	//通过父id查询用户信息
	List<SysDept>findByPid(Long id);


}
