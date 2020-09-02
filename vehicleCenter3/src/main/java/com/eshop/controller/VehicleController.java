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
import com.eshop.pojo.Vehicle;
import com.eshop.service.VehicleService;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	

	@PostMapping(value="/save")
	public HttpResult save(@RequestBody Vehicle record) {
		return HttpResult.ok(vehicleService.save(record));
	}
	
	@Log("删除车辆")
	@PreAuthorize("hasAuthority('entry:vehicle:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<Vehicle> records) {
		return HttpResult.ok(vehicleService.delete(records));
	}
	
	  @Log("查找车辆") 
	  @PreAuthorize("hasAuthority('entry:vehicle:view')")
	  @PostMapping(value="/findPage") 
	  public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		  return HttpResult.ok(vehicleService.findPage(pageRequest));
		  }

	  @Log("导出车辆数据")
	  @PreAuthorize("hasAuthority('entry:vehicle:view')")	  
	  @PostMapping(value="/exportVehicleExcelFile") 
	  public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse res) throws IOException  { 
		  		PageResult pageResult = vehicleService.findPage(pageRequest);
		  		vehicleService.downloadExcel(pageResult.getContent(), res);
	     }
}
