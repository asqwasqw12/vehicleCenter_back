package com.eshop.sys.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.eshop.sys.pojo.SysMenu;

public interface SysMenuService extends CurdService<SysMenu>{
	
	/**
	 * 查询菜单树,用户ID和用户名为空则查询全部
	 * @param menuType 获取菜单类型，0：获取所有菜单，包含按钮，1：获取所有菜单，不包含按钮
	 * @param userId 
	 * @return
	 */
	List<SysMenu> findTree(String userName, int menuType);

	/**
	 * 根据用户名查找菜单列表
	 * @param userName
	 * @return
	 */
	List<SysMenu> findByUser(String userName);
	
	//根据id查找子菜单
	List<SysMenu> findByPid(Long id);
	
	//查询子菜单
	List<SysMenu> findMenuChildren(List<SysMenu> menuList);
	
	//根据菜单名查询菜单树
	List<SysMenu> findTreebyName(String name);
	
	//导出
	void downloadExcel(List<?> records, HttpServletResponse response) throws IOException;

}
