package com.eshop.sys.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eshop.aop.Log;
import com.eshop.common.FileUtil;
import com.eshop.common.HttpResult;
import com.eshop.common.PasswordUtils;
import com.eshop.common.SecurityUtils;
import com.eshop.common.SysConstants;
import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.sys.pojo.SysUser;
import com.eshop.sys.service.SysUserService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("user")
@Slf4j
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;
	
	@Log("保存用户")
	@PreAuthorize("hasAuthority('sys:user:add') AND hasAuthority('sys:user:edit')")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody SysUser record) {
		SysUser user = sysUserService.findById(record.getId());
		if(user != null) {
			if(SysConstants.ADMIN.equalsIgnoreCase(user.getName())) {
				log.warn("超级管理员不允许修改!");
				return HttpResult.error("超级管理员不允许修改!");
			}
		}
		if(record.getPassword() != null) {
			String salt = PasswordUtils.getSalt();
			if(user == null) {
				// 新增用户
				if(sysUserService.findByName(record.getName()) != null) {
					log.warn("用户名已存在!");
					return HttpResult.ok("用户名已存在!");
				}
				String password = PasswordUtils.encode(record.getPassword(), salt);
				record.setSalt(salt);
				record.setPassword(password);
			} else {
				// 修改用户, 且修改了密码
				if(!record.getPassword().equals(user.getPassword())) {
					String password = PasswordUtils.encode(record.getPassword(), salt);
					record.setSalt(salt);
					record.setPassword(password);
				}
			}
		}
		return HttpResult.ok(sysUserService.save(record));
	}

	@Log("删除用户")
	@PreAuthorize("hasAuthority('sys:user:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysUser> records) {
		for(SysUser record:records) {
			SysUser sysUser = sysUserService.findById(record.getId());
			if(sysUser != null && SysConstants.ADMIN.equalsIgnoreCase(sysUser.getName())) {
				log.warn("超级管理员不允许删除!");
				return HttpResult.error("超级管理员不允许删除!");
			}
		}
		return HttpResult.ok(sysUserService.delete(records));
	}
	
	@Log("查找用户")
	@PreAuthorize("hasAuthority('sys:user:view')")
	@GetMapping(value="/findByName")
	public HttpResult findByUserName(@RequestParam String name) {
		return HttpResult.ok(sysUserService.findByName(name));
	}
	
	  @Log("查找用户")
	  @PreAuthorize("hasAuthority('sys:user:edit')")	  
	  @GetMapping(value="/findByStatus") 
	  public HttpResult findByStatus(@RequestParam Byte status) { 
		  //Byte userStatus = new Byte(status);
		  //return HttpResult.ok(sysUserService.findByStatus(userStatus));
		  return HttpResult.ok(sysUserService.findByStatus(status));
		  }
	 
	@Log("查找用户权限")
	@PreAuthorize("hasAuthority('sys:user:view')")
	@GetMapping(value="/findPermissions")
	public HttpResult findPermissions(@RequestParam String name) {
		return HttpResult.ok(sysUserService.findPermissions(name));
	}
	
	@Log("查找用户角色")
	@PreAuthorize("hasAuthority('sys:user:view')")
	@GetMapping(value="/findUserRoles")
	public HttpResult findUserRoles(@RequestParam Long userId) {
		return HttpResult.ok(sysUserService.findUserRoles(userId));
	}

	  @Log("查找用户")
	  @PreAuthorize("hasAuthority('sys:user:view')")	  
	  @PostMapping(value="/findPage") 
	  public HttpResult findPage(@RequestBody PageRequest pageRequest) 
	  {
		  return HttpResult.ok(sysUserService.findPage(pageRequest));
	}
	 
	
	/*
	 * @PreAuthorize("hasAuthority('sys:user:view')")
	 * 
	 * @PostMapping(value="/exportUserExcelFile") public void
	 * exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse
	 * res) { File file = sysUserService.createUserExcelFile(pageRequest);
	 * FileUtil.downloadFile(res, file, file.getName()); }
	 */
	 
	 
	/*
	 * @PreAuthorize("hasAuthority('sys:user:view')")
	 * 
	 * @PostMapping(value="/exportUserExcelFile") public void
	 * exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletRequest
	 * request, HttpServletResponse res) { File file =
	 * sysUserService.createUserExcelFile(pageRequest); //FileUtil.downloadFile(res,
	 * file, file.getName()); FileUtil.downloadFile(request,res,file,false); }
	 */
	  
	  @Log("导出用户数据")
	  @PreAuthorize("hasAuthority('sys:user:view')")	  
	  @PostMapping(value="/exportUserExcelFile") 
	  public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse res) throws IOException  { 
		  		PageResult pageResult = sysUserService.findPage(pageRequest);
		  		sysUserService.downloadExcel(pageResult.getContent(), res);
	     }
	
	@Log("更新用户密码")
	@PreAuthorize("hasAuthority('sys:user:edit')")
	@GetMapping(value="/updatePassword")
	public HttpResult updatePassword(@RequestParam String password, @RequestParam String newPassword) {
		SysUser user = sysUserService.findByName(SecurityUtils.getUsername());
		if(user == null) {
			log.warn("用户不存在!");
			HttpResult.error("用户不存在!");
		}
		if(SysConstants.ADMIN.equalsIgnoreCase(user.getName())) {
			log.warn("超级管理员不允许修改!");
			return HttpResult.error("超级管理员不允许修改!");
		}
		if(!PasswordUtils.matches(user.getSalt(), password, user.getPassword())) {
			log.warn("原密码不正确!");
			return HttpResult.error("原密码不正确!");
		}
		user.setPassword(PasswordUtils.encode(newPassword, user.getSalt()));
		return HttpResult.ok(sysUserService.save(user));
	}
	
	@Log("更新用户头像")
	@ApiOperation("修改头像")
    @PostMapping(value = "/updateAvatar")
    public HttpResult updateAvatar(@RequestParam MultipartFile file){
        sysUserService.updateAvatar(file);
        return HttpResult.ok();
    }
	
}
