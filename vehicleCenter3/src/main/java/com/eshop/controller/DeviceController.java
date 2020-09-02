package com.eshop.controller;

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
import com.eshop.pojo.Device;
import com.eshop.service.DeviceService;

@RestController
@RequestMapping("/device")
public class DeviceController {
	@Autowired
	private DeviceService deviceService;
	
	@Log("保存设备")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody Device record) {
		return HttpResult.ok(deviceService.save(record));
	}
	
	@Log("删除设备")
	@PreAuthorize("hasAuthority('entry:device:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<Device> records) {
		return HttpResult.ok(deviceService.delete(records));
	}
	
	  @Log("查找设备")	  
	  @PreAuthorize("hasAuthority('entry:device:edit')")
	  @PostMapping(value="/findPage") 
	  public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		  return HttpResult.ok(deviceService.findPage(pageRequest));
		  }

	  @Log("导出设备数据")
	  @PreAuthorize("hasAuthority('entry:device:view')")	  
	  @PostMapping(value="/exportDeviceExcelFile") 
	  public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse res) throws IOException  { 
		  		PageResult pageResult = deviceService.findPage(pageRequest);
		  		deviceService.downloadExcel(pageResult.getContent(), res);
	     }

}
