package com.eshop.sys.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshop.common.DateTimeUtils;
import com.eshop.common.FileUtil;
import com.eshop.common.SysConstants;
import com.eshop.common.page.MybatisPageHelper;
import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.sys.dao.SysMenuMapper;
import com.eshop.sys.dao.SysRoleMapper;
import com.eshop.sys.dao.SysRoleMenuMapper;
import com.eshop.sys.pojo.SysDict;
import com.eshop.sys.pojo.SysMenu;
import com.eshop.sys.pojo.SysRole;
import com.eshop.sys.pojo.SysRoleMenu;
import com.eshop.sys.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public int save(SysRole record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysRoleMapper.insertSelective(record);
		}
		return sysRoleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SysRole record) {
		return sysRoleMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysRole> records) {
		for(SysRole record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysRole findById(Long id) {
		return sysRoleMapper.selectByPrimaryKey(id);
	}

	/*
	 * @Override public PageResult findPage(PageRequest pageRequest) { Object label
	 * = pageRequest.getParamValue("name"); if(label != null) { return
	 * MybatisPageHelper.findPage(pageRequest, sysRoleMapper, "findPageByName",
	 * label); } return MybatisPageHelper.findPage(pageRequest, sysRoleMapper); }
	 */

	@Override 
	  public PageResult findPage(PageRequest pageRequest) { 
		  	PageResult pageResult = null;
			Map<String, Object> params = pageRequest.getObjectParam();
			int pageNum = pageRequest.getPageNum();
			int pageSize = pageRequest.getPageSize();
			PageHelper.startPage(pageNum, pageSize);
			List<SysRole> result = sysRoleMapper.findPageByParams(params);
			pageResult = MybatisPageHelper.getPageResult(pageRequest, new PageInfo<SysRole>((List<SysRole>) result));
			return pageResult;
	  }
	
	@Override
	public List<SysRole> findAll() {
		return sysRoleMapper.findAll();
	}

	public SysRoleMapper getSysRoleMapper() {
		return sysRoleMapper;
	}

	public void setSysRoleMapper(SysRoleMapper sysRoleMapper) {
		this.sysRoleMapper = sysRoleMapper;
	}

	@Override
	public List<SysMenu> findRoleMenus(Long roleId) {
		SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleId);
		if(SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
			// 如果是超级管理员，返回全部
			return sysMenuMapper.findAll();
		}
		return sysMenuMapper.findRoleMenus(roleId);
	}

	@Transactional
	@Override
	public int saveRoleMenus(List<SysRoleMenu> records) {
		if(records == null || records.isEmpty()) {
			return 1;
		}
		Long roleId = records.get(0).getRoleId(); //此段代码可以改进成先更新再新增再删除
		sysRoleMenuMapper.deleteByRoleId(roleId);
		for(SysRoleMenu record:records) {
			sysRoleMenuMapper.insertSelective(record);
		}
		return 1;
	}

	@Override
	public List<SysRole> findByName(String name) {
		return sysRoleMapper.findByName(name);
	}
	
	 @Override
	  public void downloadExcel(List<?> records, HttpServletResponse response) throws IOException{
		  List<Map<String, Object>> list = new ArrayList<>();
	        for (int i = 0; i <records.size(); i++) {
	        	SysRole role = (SysRole) records.get(i);
	        	Map<String,Object> map = new LinkedHashMap<>(); 
	        	map.put("ID", role.getId());
	        			 map.put("名称",role.getName()); 
	        			 map.put("备注", role.getRemark()); 
	        			 map.put("创建人",role.getCreateBy()); 
	        			 map.put("创建时间日期",DateTimeUtils.getDateTime(role.getCreateTime())); 
	        			 map.put("最后更新人",role.getLastUpdateBy()); 
	        			 map.put("最后更新时间",DateTimeUtils.getDateTime(role.getLastUpdateTime())); 
	        			 list.add(map);
	        	
	        }
	        FileUtil.downloadExcel(list, response);
	  }
}
