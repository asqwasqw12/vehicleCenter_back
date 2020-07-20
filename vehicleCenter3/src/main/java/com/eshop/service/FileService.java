package com.eshop.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.eshop.pojo.FileBean;
import com.eshop.sys.service.CurdService;

public interface FileService extends CurdService<FileBean>{
	/**
	 * 查询文件机构树
	 * @param userId 
	 * @return
	 */
	List<FileBean> findTree();
	
	List<FileBean> findTree(String name);
	
	//获取所有子Id
	List<Long> getFileChildren(Long id);
	
	//获取所有子Id
	List<Long> getFileChildren(List<FileBean> deptList);
	
	//获取子文件树
	List<FileBean> findFileChildren(List<FileBean> deptList);
	
	//上传文件
	void upload(String name,Long parentId,MultipartFile multipartFile);
	
	//导出数据
   // void downloadExcel(List<?> records, HttpServletResponse response) throws IOException;
	
	//通过父id查询用户信息
	List<FileBean>findByPid(Long id);

}
