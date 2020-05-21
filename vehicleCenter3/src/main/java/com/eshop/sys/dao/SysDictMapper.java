package com.eshop.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.eshop.sys.pojo.SysDict;
import com.eshop.sys.pojo.SysUser;



public interface SysDictMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(SysDict record);

    int insertSelective(SysDict record);

    SysDict selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDict record);

    int updateByPrimaryKey(SysDict record);
    
    //List<SysDict> findPageByLabel(@Param(value="label") String label);

   // List<SysDict> findByLable(@Param(value="label") String label);
    
    List<SysDict> findPageByParams(Map<String,Object> params);
    
    

}
