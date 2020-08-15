package com.eshop.controller;

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
import com.eshop.pojo.FileBean;
import com.eshop.service.FileService;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/file")
public class FileController {
	
@Autowired 
FileService fileService;

//是否开启共享文件模式
public static Boolean isShareFile = false;

public static long treeId = 0;

//新建文件夹、修改文件夹、修改文件名
@Log("保存文件")
@PreAuthorize("hasAuthority('file:list:add') AND hasAuthority('file:list:edit')")
@PostMapping(value="/save")
public HttpResult save(@RequestBody FileBean fileBean) {
	fileService.save(fileBean);
	return HttpResult.ok();
}

@Log("查看文件树")
@PreAuthorize("hasAuthority('file:list:view')")
@GetMapping(value="/findTree")
public HttpResult findTree(@RequestParam String name){
	if(name !=null && name !="") {
		return HttpResult.ok(fileService.findTree(name));
	}else {
		return HttpResult.ok(fileService.findTree());
	}
		/*
		 * fileBean.setFilePath(FileUtil.urlDecode(fileBean.getFilePath()));
		 * List<FileBean> fileList = fileService.selectFileList(fileBean); return
		 * HttpResult.ok(fileList);
		 */
}

@Log("删除文件")
@PreAuthorize("hasAuthority('file:list:delete')")
@PostMapping(value="/delete")
public HttpResult delete(@RequestBody List<FileBean> records) {
	fileService.delete(records);
	return HttpResult.ok();
}

@Log("上传文件")
@PreAuthorize("hasAuthority('file:list:add') AND hasAuthority('file:list:edit')")
@PostMapping(value="/upload")
public HttpResult upload(@RequestParam String name,@RequestParam Long parentId, @RequestParam("file") MultipartFile file) {
	fileService.upload(name,parentId,file);
	return HttpResult.ok();
}

@Log("下载文件")
@PreAuthorize("hasAuthority('file:list:view')")
@PostMapping(value="/download")
public void download(@RequestBody FileBean record, HttpServletRequest request, HttpServletResponse response) {
	fileService.download(record,request,response);
}

}
