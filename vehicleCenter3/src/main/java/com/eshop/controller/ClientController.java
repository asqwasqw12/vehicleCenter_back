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
import com.eshop.service.ClientService;
import com.eshop.pojo.Client;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	
	@Log("保存客户")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody Client record) {
		return HttpResult.ok(clientService.save(record));
	}
	
	
	@Log("删除客户")
	@PreAuthorize("hasAuthority('client:clientInfo:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<Client> records) {
		return HttpResult.ok(clientService.delete(records));
	}
	
	@Log("查找客户") 
	@PreAuthorize("hasAuthority('client:clientInfo:view')")
	@PostMapping(value="/findPage") 
	public HttpResult findPage(@RequestBody PageRequest pageRequest) {
		  return HttpResult.ok(clientService.findPage(pageRequest));
	}
	
	@Log("导出客户数据")
	@PreAuthorize("hasAuthority('client:clientInfo:view')")	  
	@PostMapping(value="/exportClientExcelFile") 
	public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse res) throws IOException  { 
		  		PageResult pageResult = clientService.findPage(pageRequest);
		  		clientService.downloadExcel(pageResult.getContent(), res);
	     }
}
