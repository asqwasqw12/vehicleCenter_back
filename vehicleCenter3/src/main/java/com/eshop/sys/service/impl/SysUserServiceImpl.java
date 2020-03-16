package com.eshop.sys.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.sys.dao.SysUserDao;
import com.eshop.sys.dao.SysUserRoleDao;
import com.eshop.sys.pojo.SysUser;
import com.eshop.sys.pojo.SysUserRole;
import com.eshop.sys.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {
 
	@Autowired SysUserDao sysUserDao;
	@Autowired SysUserRoleDao sysUserRoleDao;
	
	@Override
	public SysUser findById(Long id) {
		return sysUserDao.findById(id);
	}
	
	@Override
	public SysUser findByName( String name){
		return sysUserDao.findByName(name);
	}
	
	
	
	  @Override 
	  public Set<String> findPermissions(String userName) { 
		/*
		 * Set<String> perms = new HashSet<>(); List<SysMenu> sysMenus =
		 * sysMenuService.findByUser(userName); for(SysMenu sysMenu:sysMenus) {
		 * if(sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())) {
		 * perms.add(sysMenu.getPerms()); } } return perms;
		 */
	  return null; }
	 /*
	 * @Override public List<SysUserRole> findUserRoles(Long userId) { // TODO
	 * Auto-generated method stub return null; }
	 */

	@Override
	public int save(SysUser record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(SysUser record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(List<SysUser> records) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SysUserRole> findUserRoles(Long userId) {
		return sysUserRoleDao.findUserRoles(userId);
	}
}
