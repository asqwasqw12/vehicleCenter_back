package com.eshop.sys.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.sys.pojo.SysMenu;
import com.eshop.sys.pojo.SysRole;
import com.eshop.sys.pojo.SysRoleMenu;

public interface SysRoleService extends CurdService<SysRole>{
	
	/**
	 * 查询全部
	 * @return
	 */
	List<SysRole> findAll();

	/**
	 * 查询角色菜单集合
	 * @return
	 */
	List<SysMenu> findRoleMenus(Long roleId);

	/**
	 * 保存角色菜单
	 * @param records
	 * @return
	 */
	int saveRoleMenus(List<SysRoleMenu> records);

	/**
	 * 根据名称查询
	 * @param name
	 * @return
	 */
	List<SysRole> findByName(String name);
	
	//查询角色表
	PageResult findPage(PageRequest pageRequest);
	
	void downloadExcel(List<?> records, HttpServletResponse response) throws IOException;

}
