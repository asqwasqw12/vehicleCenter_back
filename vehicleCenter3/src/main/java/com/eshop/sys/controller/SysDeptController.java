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
import com.eshop.common.page.PageRequest;
import com.eshop.common.page.PageResult;
import com.eshop.sys.pojo.SysDept;
import com.eshop.sys.service.SysDeptService;


@RestController
@RequestMapping("dept")
public class SysDeptController {

	@Autowired
	private SysDeptService sysDeptService;
	
	@Log("保存部门")
	@PreAuthorize("hasAuthority('sys:dept:add') AND hasAuthority('sys:dept:edit')")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody SysDept record) {
		return HttpResult.ok(sysDeptService.save(record));
	}

	@Log("删除部门")
	@PreAuthorize("hasAuthority('sys:dept:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysDept> records) {
		return HttpResult.ok(sysDeptService.delete(records));
	}

	@Log("查找部门树")
	@PreAuthorize("hasAuthority('sys:dept:view')")
	@GetMapping(value="/findTree")
	public HttpResult findTree(@RequestParam String name) {
		if(name !=null && name !="") {
			return HttpResult.ok(sysDeptService.findTree(name));
		}else {
			return HttpResult.ok(sysDeptService.findTree());
		}
	}
	
	  @Log("导出部门数据")
	  @PreAuthorize("hasAuthority('sys:dept:view')")	  
	  @PostMapping(value="/exportDeptExcelFile") 
	  public void exportExcelUser(@RequestBody PageRequest pageRequest, HttpServletResponse res) throws IOException  { 
		  		List<SysDept> list= sysDeptService.findTree();
		  		sysDeptService.downloadExcel(list, res);
	     }

}
