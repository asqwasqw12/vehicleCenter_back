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
import com.eshop.pojo.VehicleOutStorage;
import com.eshop.service.VehicleOutStorageService;

@RestController
@RequestMapping("/outStorage")
public class VehicleOutStorageController {
	
	@Autowired
	private VehicleOutStorageService vehicleOutStorageService;
	
	@Log("保存入库车辆")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody List<VehicleOutStorage> records) {
		return HttpResult.ok(vehicleOutStorageService.save(records));
	}
	
	
	@Log("删除入库车辆")
	@PreAuthorize("hasAuthority('entry:inStorage:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<VehicleOutStorage> records) {
		return HttpResult.ok(vehicleOutStorageService.delete(records));
	}
	
	@Log("查找入库车辆") 
	@PreAuthorize("hasAuthority('entry:inStorage:view')")
	@PostMapping(value="/findPage") 
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		  return HttpResult.ok(vehicleOutStorageService.findPage(pageRequest));
	}
	
	@Log("导出车辆数据")
	@PreAuthorize("hasAuthority('entry:vehicle:view')")	  
	@PostMapping(value="/exportInStorageExcelFile") 
	public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse res) throws IOException  { 
		  		PageResult pageResult = vehicleOutStorageService.findPage(pageRequest);
		  		vehicleOutStorageService.downloadExcel(pageResult.getContent(), res);
	     }


}
