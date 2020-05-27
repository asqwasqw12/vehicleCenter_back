package com.eshop.sys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.aop.Log;
import com.eshop.common.HttpResult;
import com.eshop.common.SysConstants;
import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.sys.dao.SysRoleMapper;
import com.eshop.sys.pojo.SysRole;
import com.eshop.sys.pojo.SysRoleMenu;
import com.eshop.sys.service.SysRoleService;



@RestController
@RequestMapping("role")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Log("保存角色")
	@PreAuthorize("hasAuthority('sys:role:add') AND hasAuthority('sys:role:edit')")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody SysRole record) {
		SysRole role = sysRoleService.findById(record.getId());
		if(role != null) {
			if(SysConstants.ADMIN.equalsIgnoreCase(role.getName())) {
				return HttpResult.error("超级管理员不允许修改!");
			}
		}
		// 新增角色
		if((record.getId() == null || record.getId() ==0) && !sysRoleService.findByName(record.getName()).isEmpty()) {
			return HttpResult.error("角色名已存在!");
		}
		return HttpResult.ok(sysRoleService.save(record));
	}
	
	@Log("删除角色")
	@PreAuthorize("hasAuthority('sys:role:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysRole> records) {
		return HttpResult.ok(sysRoleService.delete(records));
	}

	  @Log("查找角色")
	  @PreAuthorize("hasAuthority('sys:role:view')")	  
	  @PostMapping(value="/findPage") 
	  public HttpResult findPage(@RequestBody PageRequest pageRequest) { 
		  return HttpResult.ok(sysRoleService.findPage(pageRequest)); 
		  }
	 
	@Log("查找所有角色")
	@PreAuthorize("hasAuthority('sys:role:view')")
	@GetMapping(value="/findAll")
	public HttpResult findAll() {
		return HttpResult.ok(sysRoleService.findAll());
	}
	
	@Log("查找角色菜单")
	@PreAuthorize("hasAuthority('sys:role:view')")
	@GetMapping(value="/findRoleMenus")
	public HttpResult findRoleMenus(@RequestParam Long roleId) {
		return HttpResult.ok(sysRoleService.findRoleMenus(roleId));
	}
	
	@Log("保存角色菜单")
	@PreAuthorize("hasAuthority('sys:role:view')")
	@PostMapping(value="/saveRoleMenus")
	public HttpResult saveRoleMenus(@RequestBody List<SysRoleMenu> records) {
		for(SysRoleMenu record:records) {
			SysRole sysRole = sysRoleMapper.selectByPrimaryKey(record.getRoleId());
			if(SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
				// 如果是超级管理员，不允许修改
				return HttpResult.error("超级管理员拥有所有菜单权限，不允许修改！");
			}
		}
		return HttpResult.ok(sysRoleService.saveRoleMenus(records));
	}
	
	  @Log("导出角色数据")
	  @PreAuthorize("hasAuthority('sys:role:view')")	  
	  @PostMapping(value="/exportRoleExcelFile") 
	  public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse res) throws IOException  { 
		  		PageResult pageResult = sysRoleService.findPage(pageRequest);
		  		sysRoleService.downloadExcel(pageResult.getContent(), res);
	     }
}
