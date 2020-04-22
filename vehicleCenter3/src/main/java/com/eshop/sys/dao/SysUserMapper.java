package com.eshop.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.eshop.sys.pojo.SysUser;


public interface SysUserMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);
    
    List<SysUser> selectByStatus(Byte status);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    List<SysUser> findPage();
    
    SysUser findByName(@Param(value="name") String name);
    
	List<SysUser> findPageByName(@Param(value="name") String name);
	
	List<SysUser> findPageByNameAndEmail(@Param(value="name") String name, @Param(value="email") String email);
	
	List<SysUser> findPageByParams(Map<String,Object>  params);
	//List<SysUser> findPageByParams(SysUser  map);

}
