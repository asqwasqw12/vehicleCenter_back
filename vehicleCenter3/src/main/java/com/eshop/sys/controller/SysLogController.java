package com.eshop.sys.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import com.eshop.sys.pojo.SysLog;
import com.eshop.sys.service.SysLogService;



@RestController
@RequestMapping("log")
public class SysLogController {

	@Autowired
	private SysLogService sysLogService;

	@PreAuthorize("hasAuthority('sys:log:view')")
	@PostMapping(value="/findPage")
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		return HttpResult.ok(sysLogService.findPage(pageRequest));
	}
	
	/*
	 * @Log("删除日志")
	 * 
	 * @PreAuthorize("hasAuthority('sys:log:delete')")
	 * 
	 * @PostMapping(value="/delete") public HttpResult delete(@RequestBody
	 * List<SysLog> records) { return HttpResult.ok(sysLogService.delete(records));
	 * }
	 */
	
	@Log("删除日志")
	@PreAuthorize("hasAuthority('sys:log:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody Map<String,Object> params) {
		return HttpResult.ok(sysLogService.delete(params));
	}
	
	  @Log("导出日志数据")
	  @PreAuthorize("hasAuthority('sys:log:view')")	  
	  @PostMapping(value="/exportLogExcelFile") 
	  public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse res) throws IOException  { 
		  		PageResult pageResult = sysLogService.findPage(pageRequest);
		  		sysLogService.downloadExcel(pageResult.getContent(), res);
	     }
}
