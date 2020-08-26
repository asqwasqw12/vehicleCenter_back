package com.eshop.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.eshop.sys.pojo.SysDept;


public interface SysDeptMapper {
	
	    int deleteByPrimaryKey(Long id);

	    int insert(SysDept record);

	    int insertSelective(SysDept record);

	    SysDept selectByPrimaryKey(Long id);

	    int updateByPrimaryKeySelective(SysDept record);

	    int updateByPrimaryKey(SysDept record);
	    
	    List<SysDept> findByPid(Long id); 
	    
	    List<SysDept> findPageByParams(Map<String,Object> params);
	    
	    List<SysDept> findByName(@Param(value="name") String name);
	    	    
	    List<SysDept> findAll();

}
