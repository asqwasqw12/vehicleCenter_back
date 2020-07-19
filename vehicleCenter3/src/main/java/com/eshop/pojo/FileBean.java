package com.eshop.pojo;

import java.util.List;

import org.springframework.data.annotation.Transient;


public class FileBean extends BasePojo {
	private Long userId;		//用户id
	private String name;		//文件名
	private String realName;	//真实文件名
	private String extendName;	//扩展名
	private String type;		//文件类型
	private Long parentId;		//父id
	private String fileUrl;		//文件后端地址
	private Long fileSize;		//文件大小
	private int isDir;			//目录or文件
	private int isShare;		//共享or私人
	// 非数据库字段
	@Transient
	private String frontPath;	//前端文件路径
	// 非数据库字段
	@Transient
    private List<FileBean> children;
    // 非数据库字段
	@Transient
    private String parentName;
    // 非数据库字段
	@Transient
    private Integer level;
    
    
    public Long getParentId() {
    	return parentId;
    }
    
    public void setParentId(Long parentId) {
    	this.parentId = parentId;
    }
    
    public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRealName() {
		return realName;
	}
	
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public String getExtendName() {
		return extendName;
	}
	
	public void setExtendName(String extendName) {
		this.extendName = extendName;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getFileUrl() {
		return fileUrl;
	}
	
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	public Long getFileSize() {
		return fileSize;
	}
	
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
	public int getIsDir() {
		return isDir;
	}
	
	public void setIsDir(int isDir) {
		this.isDir = isDir;
	}
	
	public int getIsShare() {
		return isShare;
	}
	
	public void setIsShare(int isShare) {
		this.isShare = isShare;
	}
	
	public String getFrontPath() {
		return frontPath;
	}
	
	public void setFrontPath(String frontPath) {
		this.frontPath = frontPath;
	}
	
	public List<FileBean> getChildren() {
		return children;
	}
	public void setChildren(List<FileBean> children) {
		this.children = children;
	}
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
}
