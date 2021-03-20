package com.eshop.sys.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.aop.Log;
import com.eshop.common.HttpResult;
import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.sys.pojo.OnlineUser;
import com.eshop.sys.service.OnlineUserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("online")
public class OnlineController {
	@Autowired
	OnlineUserService onlineUserService;
	
	@Log("查询在线用户")
	@PreAuthorize("hasAuthority('sys:online:view')")	  
	@PostMapping(value="/findPage") 
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		  return HttpResult.ok(onlineUserService.findPage(pageRequest));
	}
	
	 @Log("导出在线用户")
	 @PreAuthorize("hasAuthority('sys:online:view')")	  
	 @PostMapping(value="/exportOnlineExcelFile") 
	 public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse res) throws IOException  { 
		  		PageResult pageResult = onlineUserService.findPage(pageRequest);
		  		onlineUserService.downloadExcel((List<OnlineUser>) pageResult.getContent(), res);
	     }
}
