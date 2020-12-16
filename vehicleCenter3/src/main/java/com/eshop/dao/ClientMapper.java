package com.eshop.dao;

import java.util.List;
import java.util.Map;

import com.eshop.pojo.Client;

public interface ClientMapper {
	
	int deleteByPrimaryKey(Long id);

    int insert(Client record);

    int insertSelective(Client record);

    Client selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Client record);

    int updateByPrimaryKey(Client record);
    
    List<Client> findPageByParams(Map<String,Object> params);
    
    List<Client> findByCompanyId(Long companyId);
    
    List<Client> findPage();

}
