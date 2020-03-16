package com.eshop.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.eshop.sys.pojo.SysUserRole;



public interface SysUserRoleDao {
	
	//根据用户id查找用户角色集合
	@Select("select id, user_id, role_id, create_by, create_time, last_update_by, last_update_time from sys_user_role where user_id = #{userId}")
	List<SysUserRole> findUserRoles(@Param(value="userId") Long userId);
	
	
	

}
