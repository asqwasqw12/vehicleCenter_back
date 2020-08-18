package com.eshop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.eshop.pojo.Company;



public interface CompanyMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
    
    List<Company> findPageByParams(Map<String,Object> params);
    
    List<Company> findByPid(Long id); 
    
    List<Company> findByName(@Param(value="name") String name);
    	    
    List<Company> findAll();

}
