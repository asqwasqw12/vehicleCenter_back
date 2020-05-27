package com.eshop.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.eshop.sys.pojo.SysLog;


public interface SysLogMapper {
	
	 int deleteByPrimaryKey(Long id);
	 int deleteByParams(Map<String,Object> params);

	    int insert(SysLog record);

	    int insertSelective(SysLog record);

	    SysLog selectByPrimaryKey(Long id);

	    int updateByPrimaryKeySelective(SysLog record);

	    int updateByPrimaryKey(SysLog record);
	    
	    List<SysLog> findPage();
	    
	    List<SysLog> findPageByParams(Map<String,Object> params);
	    
	    List<SysLog> findPageByUserName(@Param(value="userName") String userName);

}
