package com.eshop.sys.service.impl;

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
import com.eshop.sys.dao.SysDictMapper;
import com.eshop.sys.pojo.SysDict;
import com.eshop.sys.pojo.SysUser;
import com.eshop.sys.service.SysDictService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SysDictServiceImpl implements SysDictService {

	@Autowired
	private SysDictMapper sysDictMapper;

	@Override
	public int save(SysDict record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysDictMapper.insertSelective(record);
		}
		return sysDictMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SysDict record) {
		return sysDictMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysDict> records) {
		for(SysDict record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysDict findById(Long id) {
		return sysDictMapper.selectByPrimaryKey(id);
	}

	
	  @Override 
	  public PageResult findPage(PageRequest pageRequest) { 
		  	PageResult pageResult = null;
			Map<String, Object> params = pageRequest.getObjectParam();
			int pageNum = pageRequest.getPageNum();
			int pageSize = pageRequest.getPageSize();
			PageHelper.startPage(pageNum, pageSize);
			List<SysDict> result = sysDictMapper.findPageByParams(params);
			pageResult = MybatisPageHelper.getPageResult(pageRequest, new PageInfo<SysDict>((List<SysDict>) result));
			return pageResult;
	  }
	 
	  @Override
	  public void downloadExcel(List<?> records, HttpServletResponse response) throws IOException{
		  List<Map<String, Object>> list = new ArrayList<>();
	        for (int i = 0; i <records.size(); i++) {
	        	SysDict dict = (SysDict) records.get(i);
	        	Map<String,Object> map = new LinkedHashMap<>(); 
	        	map.put("ID", dict.getId());
	        			 map.put("名称",dict.getLabel()); 
	        			 map.put("值", dict.getValue()); 
	        			 map.put("类型",dict.getType()); 
	        			 map.put("排序", dict.getSort()); 
	        			 map.put("描述",dict.getDescription()); 
	        			 map.put("备注", dict.getRemarks()); 
	        			 map.put("创建人",dict.getCreateBy()); 
	        			 map.put("创建时间日期",DateTimeUtils.getDateTime(dict.getCreateTime())); 
	        			 map.put("最后更新人",dict.getLastUpdateBy()); 
	        			 map.put("最后更新时间",DateTimeUtils.getDateTime(dict.getLastUpdateTime())); 
	        			 list.add(map);
	        	
	        }
	        FileUtil.downloadExcel(list, response);
	  }

	/*
	 * @Override public List<SysDict> findByLable(String lable) { return
	 * sysDictMapper.findByLable(lable); }
	 */


}
