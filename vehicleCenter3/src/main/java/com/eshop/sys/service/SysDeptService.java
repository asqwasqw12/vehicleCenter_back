package com.eshop.sys.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.eshop.sys.pojo.SysDept;
import com.eshop.sys.pojo.SysMenu;

public interface SysDeptService extends CurdService<SysDept>{
	
	/**
	 * 查询机构树
	 * @param userId 
	 * @return
	 */
	List<SysDept> findTree();
	
	List<SysDept> findTree(String name);
	
	//获取所有子Id
	List<Long> getDeptChildren(Long id);
	
	//获取所有子Id
	List<Long> getDeptChildren(List<SysDept> deptList);
	
	//获取子SysDept树
	List<SysDept> findDeptChildren(List<SysDept> deptList);
	
	//导出数据
    void downloadExcel(List<?> records, HttpServletResponse response) throws IOException;
	
	//通过父id查询用户信息
	List<SysDept>findByPid(Long id);
	


}
