package com.eshop.sys.service;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.eshop.sys.pojo.SysUser;
import com.eshop.sys.pojo.SysUserRole;



public interface SysUserService extends CurdService<SysUser> {
	
	
	SysUser findByName(String username);

	/**
	 * 查找用户的菜单权限标识集合
	 * @param userName
	 * @return
	 */
	Set<String> findPermissions(String userName);

	/**
	 * 查找用户的角色集合
	 * @param userName
	 * @return
	 */
	List<SysUserRole> findUserRoles(Long userId);

	/**
	 * 生成用户信息Excel文件
	 * @param pageRequest 要导出的分页查询参数
	 * @return
	 */
	//File createUserExcelFile(PageRequest pageRequest);


}
