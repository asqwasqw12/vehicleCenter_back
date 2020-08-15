package com.eshop.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.eshop.common.DateTimeUtils;
import com.eshop.common.FileUtil;
import com.eshop.common.SecurityUtils;
import com.eshop.common.StringUtils;
import com.eshop.dao.FileMapper;
import com.eshop.exception.VehicleCenterException;
import com.eshop.pojo.FileBean;
import com.eshop.service.FileService;
import com.eshop.sys.dao.SysUserMapper;
import com.eshop.sys.pojo.SysUser;

import cn.hutool.core.util.ObjectUtil;


@Service
@CacheConfig(cacheNames = {"fileBean"})
public class FileServiceImpl implements FileService {
	@Autowired
	FileMapper fileMapper;
	@Autowired
	SysUserMapper sysUserMapper;
	
	 @Value("${file.path}")
	 private String path;

	  @Value("${file.maxSize}")
	  private long maxSize;
	
	@Override
	public int save(FileBean record) {
		if(record.getId() == null || record.getId() == 0) {
			return fileMapper.insertSelective(record);
		}
		return fileMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(FileBean record) {
		return fileMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<FileBean> records) {
		for(FileBean record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public FileBean findById(Long id) {
		return fileMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<FileBean> findTree() {
		List<FileBean> records = new ArrayList<>();
		List<FileBean> fileList = fileMapper.findAll();
		for (FileBean fileBean : fileList) {
			if (fileBean.getParentId() == null || fileBean.getParentId() == 0) {
				fileBean.setLevel(0);
				fileBean.setFrontPath(findFrontPath(fileBean.getId()));
				records.add(fileBean);
			}
		}
		findChildren(records, fileList);
		return records;
	}
	
	@Override
	public List<FileBean> findTree(String name){
		List<FileBean> records = new ArrayList<>();
		List<FileBean> fileList = fileMapper.findByName(name);
		records =findFileChildren(fileList);
		return records;
	}
	
	@Override
	@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
	public void upload(String name,Long parentId,MultipartFile multipartFile) {
		    FileUtil.checkSize(maxSize, multipartFile.getSize());
	        String extendName = FileUtil.getExtensionName(multipartFile.getOriginalFilename());
	        String type = FileUtil.getFileType(extendName);
	        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
	        String backPath = type + File.separator + formater.format(new Date()) + File.separator;  //比如图片，保存的文件地址为图片/20200720/
	        File file = FileUtil.upload(multipartFile, path+backPath);
	        SysUser user = sysUserMapper.findByName(SecurityUtils.getUsername());
	        if(ObjectUtil.isNull(file)){
	            throw new VehicleCenterException("上传失败");
	        }
	        try {
	            name = StringUtils.isBlank(name) ? FileUtil.getFileNameNoEx(multipartFile.getOriginalFilename()) : name;
	            FileBean fileBean =new FileBean();
	            fileBean.setName(name);
	            fileBean.setParentId(parentId);
	            fileBean.setRealName(file.getName());
	            fileBean.setExtendName(extendName);
	            fileBean.setType(type);
	            fileBean.setFileSize(multipartFile.getSize());
	            fileBean.setUserId(user.getId());
	            fileBean.setFileUrl(backPath);
	            save(fileBean);
	        }catch (Exception e){
	            FileUtil.del(file);
	            throw e;
	        }
	}
	
	@Override
	public void download(FileBean record,HttpServletRequest request, HttpServletResponse response)  {
		String fileName = null;// 文件名
		fileName = record.getRealName();
        if (fileName != null) {
            try {
            // getCanonicalFile 可解析正确各种路径
            File dest = new File(path + record.getFileUrl()+fileName).getCanonicalFile();
            if (dest.exists()) {
            	FileUtil.downloadFile(request, response, dest, false);
             }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
	
	private String findFrontPath(Long id) {
		String frontPath = new String();
		List<String> list = new ArrayList<>();
		FileBean record = fileMapper.selectByPrimaryKey(id);
		if(record!=null) {
			frontPath = "/";
		 while(record.getParentId()!=null && record.getParentId() != 0) {			
			record = fileMapper.selectByPrimaryKey(record.getParentId());
			list.add(record.getName());
		}
		 String strTemp = new String();
		 for(int i=list.size()-1;i>=0;i--) {
			 frontPath = frontPath + strTemp + list.get(i) ;
			 strTemp = "/";
		 }
		}
		return frontPath;
	}

	private void findChildren(List<FileBean> records, List<FileBean> fileList) {
		for (FileBean record : records) {
			List<FileBean> children = new ArrayList<>();
			for (FileBean fileBean : fileList) {
				if (record.getId() != null && record.getId().equals(fileBean.getParentId())) {
					fileBean.setParentName(record.getName());
					fileBean.setLevel(record.getLevel() + 1);
					fileBean.setFrontPath(findFrontPath(fileBean.getId()));
					children.add(fileBean);
				}
			}
			record.setChildren(children);
			findChildren(children, fileList);
		}
	}
	
	@Override
	public List<Long> getFileChildren(Long id){
		FileBean fileBean = findById(id);
		List<FileBean> records = fileMapper.findAll();
		List<Long> list =new ArrayList<>();
		List<Long> temp =new ArrayList<>();
		if(fileBean !=null) {
		for(FileBean record : records) {
			if(fileBean.getId() != null && fileBean.getId().equals(record.getParentId())) {
				temp.add(record.getId());
			}			
		}
		if(temp.size()!=0) {
		 list.addAll(temp);
		  for(Long ChildId :temp) {
			list.addAll(getFileChildren(ChildId));
		  }
		}
		}
		return list;
	}
	
	
	
	@Override
	public List<Long> getFileChildren(List<FileBean> fileList) {
        List<Long> list = new ArrayList<>();
        fileList.forEach(fileBean -> {
                    if (fileBean !=null ){
                        List<FileBean> records = fileMapper.findByPid(fileBean.getId());
                        if(fileList.size() != 0){
                            list.addAll(getFileChildren(records));
                        }
                        list.add(fileBean.getId());
                    }
                }
        );
        return list;
    }
	
	@Override
	public List<FileBean> findFileChildren(List<FileBean> fileList) {
		List<FileBean> list = new ArrayList<>();
		fileList.forEach(fileBean -> {
            if (fileBean!=null ){
                List<FileBean> records = fileMapper.findByPid(fileBean.getId());
                if(fileList.size() != 0){
                	fileBean.setChildren(findFileChildren(records));
                    //findDeptChildren(depts);
                }
                if(fileBean.getParentId()!=null && fileBean.getParentId()!=0 ){
                		FileBean temp=fileMapper.selectByPrimaryKey(fileBean.getParentId());
                		fileBean.setParentName(temp.getName());
                }
                list.add(fileBean);
                
            }
        }
	);
        return list;
	}
	
	//通过父id查询用户信息
	@Override
	public List<FileBean> findByPid(Long id){
		return fileMapper.findByPid(id);
	}
	
	/*
	 * @Resource FiletransferService filetransferService;
	 */

	/*
	 * @Override public void insertFile(FileBean fileBean) {
	 * fileMapper.insertFile(fileBean); }
	 * 
	 * @Override public void batchInsertFile(List<FileBean> fileBeanList) {
	 * 
	 * UserBean sessionUserBean = (UserBean)
	 * SecurityUtils.getSubject().getPrincipal(); StorageBean storageBean =
	 * filetransferService.selectStorageBean(new
	 * StorageBean(sessionUserBean.getUserId()));
	 * 
	 * //String userName = SecurityUtils.getUsername(); //SysUser user =
	 * sysUserService.findByName(userName); long fileSizeSum = 0; for (FileBean
	 * fileBean : fileBeanList) { if (fileBean.getIsDir() == 0) { fileSizeSum +=
	 * fileBean.getFileSize(); } } fileMapper.batchInsertFile(fileBeanList);
	 * 
	 * if (storageBean != null) { long updateFileSize = storageBean.getStoragesize()
	 * + fileSizeSum;
	 * 
	 * storageBean.setStoragesize(updateFileSize);
	 * filetransferService.updateStorageBean(storageBean); }
	 * 
	 * }
	 * 
	 * @Override public void updateFile(FileBean fileBean) {
	 * fileMapper.updateFile(fileBean); }
	 * 
	 * @Override public FileBean selectFileById(FileBean fileBean) { return
	 * fileMapper.selectFileById(fileBean); }
	 * 
	 * 
	 * @Override public List<FileBean> selectFilePathTreeByUserId(FileBean fileBean)
	 * { return fileMapper.selectFilePathTreeByUserId(fileBean); }
	 * 
	 * 
	 * @Override public List<FileBean> selectFileList(FileBean fileBean) { return
	 * fileMapper.selectFileList(fileBean); }
	 * 
	 * @Override public List<FileBean> selectFileListByIds(List<Integer> fileidList)
	 * { return fileMapper.selectFileListByIds(fileidList); }
	 * 
	 * @Override public List<FileBean> selectFileTreeListLikeFilePath(String
	 * filePath) { FileBean fileBean = new FileBean(); filePath =
	 * filePath.replace("\\", "\\\\\\\\"); filePath = filePath.replace("'", "\\'");
	 * filePath = filePath.replace("%", "\\%"); filePath = filePath.replace("_",
	 * "\\_"); fileBean.setFilePath(filePath);
	 * 
	 * return fileMapper.selectFileTreeListLikeFilePath(fileBean); }
	 * 
	 * @Override public void delete(FileBean fileBean) { // UserBean sessionUserBean
	 * = (UserBean) SecurityUtils.getSubject().getPrincipal(); //StorageBean
	 * storageBean = filetransferService.selectStorageBean(new
	 * StorageBean(sessionUserBean.getUserId())); long deleteSize = 0; String
	 * fileUrl = FileUtil.getStaticPath() + fileBean.getFileUrl(); if
	 * (fileBean.getIsDir() == 1) { //1、先删除子目录 String filePath =
	 * fileBean.getFilePath() + fileBean.getName() + "/"; List<FileBean> fileList =
	 * selectFileTreeListLikeFilePath(filePath);
	 * 
	 * for (int i = 0; i < fileList.size(); i++){ FileBean file = fileList.get(i);
	 * //1.1、删除数据库文件 fileMapper.deleteFileById(file); //1.2、如果是文件，需要记录文件大小 if
	 * (file.getIsDir() != 1){ deleteSize += file.getFileSize();
	 * //1.3、删除服务器文件，只删除文件，目录是虚拟的 if (file.getFileUrl() != null &&
	 * file.getFileUrl().indexOf("upload") != -1){
	 * FileUtil.del(FileUtil.getStaticPath() + file.getFileUrl()); } } } //2、根目录单独删除
	 * fileMapper.deleteFileById(fileBean); }else{
	 * fileMapper.deleteFileById(fileBean); deleteSize =
	 * FileUtil.size(FileUtil.file(fileUrl)); //删除服务器文件 if (fileBean.getFileUrl() !=
	 * null && fileBean.getFileUrl().indexOf("upload") != -1){
	 * FileUtil.del(FileUtil.file(fileUrl)); } }
	 * 
	 * 
	 * if (storageBean != null) { long updateFileSize = storageBean.getStoragesize()
	 * - deleteSize; if (updateFileSize < 0) { updateFileSize = 0; }
	 * storageBean.setStoragesize(updateFileSize);
	 * filetransferService.updateStorageBean(storageBean); }
	 * 
	 * }
	 * 
	 * @Override public void delete(List<FileBean> records) { for(FileBean
	 * record:records) { delete(record); } }
	 * 
	 * @Override public void deleteFileByIds(List<Integer> idList) {
	 * fileMapper.deleteFileByIds(idList); }
	 * 
	 * 
	 * @Override public void updateFilepathByFilepath(String oldfilepath, String
	 * newfilepath, String filename, String extendname) { if
	 * ("null".equals(extendname)){ extendname = null; } //移动根目录
	 * fileMapper.updateFilepathByPathAndName(oldfilepath, newfilepath, filename,
	 * extendname);
	 * 
	 * //移动子目录 oldfilepath = oldfilepath + filename + "/"; newfilepath = newfilepath
	 * + filename + "/";
	 * 
	 * oldfilepath = oldfilepath.replace("\\", "\\\\\\\\"); oldfilepath =
	 * oldfilepath.replace("'", "\\'"); oldfilepath = oldfilepath.replace("%",
	 * "\\%"); oldfilepath = oldfilepath.replace("_", "\\_");
	 * 
	 * if (extendname == null) { //为null说明是目录，则需要移动子目录
	 * fileMapper.updateFilepathByFilepath(oldfilepath, newfilepath); }
	 * 
	 * }
	 */

	/*
	 * @Override public List<FileBean> selectFileByExtendName(List<String>
	 * filenameList, long userid) { return
	 * fileMapper.selectFileByExtendName(filenameList, userid); }
	 */

}
