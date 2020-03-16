package com.eshop.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.eshop.sys.pojo.SysUser;
import com.eshop.sys.pojo.SysUserRole;


public interface SysUserDao {
	//通过id查找用户
	@Select("select * from sys_user where id=#{id}")
	SysUser findById(Long id);
	
	//通过姓名
	@Select("select u.*,(select d.name from sys_dept d where d.id = u.dept_id) deptName from sys_user u " + 
			"where u.name = #{name}")
	SysUser findByName(@Param(value="name") String name);
	
	//查找用户角色集合
	
	List<SysUserRole> findUserRoles(Long userId);
	
	

}
