package com.eshop.sys.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.sys.pojo.SysUser;
import com.eshop.sys.pojo.SysUserRole;



public interface SysUserService extends CurdService<SysUser> {
	
	//通过用户名查询用户
	SysUser findByName(String username);
	
	//通过用户状态查询用户
	List<SysUser> findByStatus(Byte status);

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
	File createUserExcelFile(PageRequest pageRequest);
	
	//更新用户头像
	void updateAvatar(MultipartFile file);
	
	//查询用户信息
	public PageResult findPage(PageRequest pageRequest);
	

}
