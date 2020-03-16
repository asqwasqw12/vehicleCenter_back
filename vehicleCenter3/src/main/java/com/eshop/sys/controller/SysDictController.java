package com.eshop.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.common.HttpResult;
import com.eshop.sys.pojo.SysDict;
import com.eshop.sys.service.SysDictService;


@RestController
@RequestMapping("dict")
public class SysDictController {

	@Autowired
	private SysDictService sysDictService;
	
	@PreAuthorize("hasAuthority('sys:dict:add') AND hasAuthority('sys:dict:edit')")
	@PostMapping(value="/save")
	public HttpResult save(@RequestBody SysDict record) {
		return HttpResult.ok(sysDictService.save(record));
	}

	@PreAuthorize("hasAuthority('sys:dict:delete')")
	@PostMapping(value="/delete")
	public HttpResult delete(@RequestBody List<SysDict> records) {
		return HttpResult.ok(sysDictService.delete(records));
	}

	/*
	 * @PreAuthorize("hasAuthority('sys:dict:view')")
	 * 
	 * @PostMapping(value="/findPage") public HttpResult findPage(@RequestBody
	 * PageRequest pageRequest) { return
	 * HttpResult.ok(sysDictService.findPage(pageRequest)); }
	 */
	
	/*
	 * @PreAuthorize("hasAuthority('sys:dict:view')")
	 * 
	 * @GetMapping(value="/findByLable") public HttpResult findByLable(@RequestParam
	 * String lable) { return HttpResult.ok(sysDictService.findByLable(lable)); }
	 */
}
