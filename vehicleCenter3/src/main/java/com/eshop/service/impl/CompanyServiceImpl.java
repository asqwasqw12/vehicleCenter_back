package com.eshop.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshop.common.DateTimeUtils;
import com.eshop.common.FileUtil;
import com.eshop.common.page.MybatisPageHelper;
import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.dao.CompanyMapper;
import com.eshop.pojo.Company;
import com.eshop.service.CompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CompanyServiceImpl  implements CompanyService {
	
	@Autowired
	CompanyMapper companyMapper;
	
	@Override
	public int save(Company record) {
		if(record.getId() == null || record.getId() == 0) {
			return companyMapper.insertSelective(record);
		}
		return companyMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(Company record) {
		return companyMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<Company> records) {
		for(Company record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public Company findById(Long id) {
		return companyMapper.selectByPrimaryKey(id);
	}

	
	  @Override 
	  public PageResult findPage(PageRequest pageRequest) { 
		  	PageResult pageResult = null;
			Map<String, Object> params = pageRequest.getObjectParam();
			int pageNum = pageRequest.getPageNum();
			int pageSize = pageRequest.getPageSize();
			PageHelper.startPage(pageNum, pageSize);
			List<Company> result = companyMapper.findPageByParams(params);
			pageResult = MybatisPageHelper.getPageResult(pageRequest, new PageInfo<Company>((List<Company>) result));
			return pageResult;
	  }
	 
	  @Override
	  public void downloadExcel(List<?> records, HttpServletResponse response) throws IOException{
		  List<Map<String, Object>> list = new ArrayList<>();
	        for (int i = 0; i <records.size(); i++) {
	        	Company company = (Company) records.get(i);
	        	Map<String,Object> map = new LinkedHashMap<>(); 
	        	map.put("ID", company.getId());
	        			 map.put("名称",company.getName());  	        			 
	        			 map.put("地址", company.getAddress()); 
	        			 map.put("电话",company.getTelephone()); 
	        			 map.put("网址",company.getWebsite()); 
	        			 map.put("行业",company.getIndustry());
	        			 map.put("备注",company.getRemarks());
	        			 map.put("创建人",company.getCreateBy()); 
	        			 map.put("创建时间日期",DateTimeUtils.getDateTime(company.getCreateTime())); 
	        			 map.put("最后更新人",company.getLastUpdateBy()); 
	        			 map.put("最后更新时间",DateTimeUtils.getDateTime(company.getLastUpdateTime())); 
	        			 list.add(map);
	        	
	        }
	        FileUtil.downloadExcel(list, response);
	  }

}
