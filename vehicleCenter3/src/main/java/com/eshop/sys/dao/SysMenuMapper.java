package com.eshop.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.eshop.sys.pojo.SysMenu;


public interface SysMenuMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
	
	List<SysMenu> findAll();

	List<SysMenu> findByUserName(@Param(value="userName") String userName);

	List<SysMenu> findRoleMenus(@Param(value="roleId") Long roleId);

}
