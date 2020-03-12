package com.eshop.sys.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.eshop.sys.pojo.SysUser;


public interface SysUserDao {
	
	@Select("select * from sys_user where id=#{id}")
	SysUser findById(Long id);
	
	@Select("select u.*,(select d.name from sys_dept d where d.id = u.dept_id) deptName from sys_user u " + 
			"where u.name = #{name}")
	SysUser findByName(@Param(value="name") String name);
	
	

}
