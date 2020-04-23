package com.eshop.sys.dao;

import java.util.List;

import com.eshop.sys.pojo.SysDept;


public interface SysDeptMapper {
	
	    int deleteByPrimaryKey(Long id);

	    int insert(SysDept record);

	    int insertSelective(SysDept record);

	    SysDept selectByPrimaryKey(Long id);

	    int updateByPrimaryKeySelective(SysDept record);

	    int updateByPrimaryKey(SysDept record);
	    
	    List<SysDept> findByPid(Long id); 
	    	    
	    List<SysDept> findAll();

}
