package com.eshop.sys.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.sys.dao.SysUserDao;
import com.eshop.sys.pojo.SysUser;
import com.eshop.sys.pojo.SysUserRole;
import com.eshop.sys.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {
 
	@Autowired SysUserDao sysUserDao;
	
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
}
