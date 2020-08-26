package com.eshop.sys.pojo;

import java.util.List;



public class SysMenu extends BaseModel {
	
	 private Long parentId;    //父菜单

	    private String name;  //菜单名称

	    private String url;  //菜单URL,类型：1.普通页面（如用户管理， /sys/user） 2.嵌套完整外部页面，以http(s)开头的链接 3.嵌套服务器页面，使用iframe:前缀+目标URL(如SQL监控， iframe:/druid/login.html, iframe:前缀会替换成服务器地址)
	    
	    private String location;  //vue组件位置

	    private String perms;   //授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)

	    private Integer type;  //类型   0：目录   1：菜单   2：按钮

	    private String icon;  //菜单图标

	    private Integer orderNum; //排序

	    private Byte delFlag;  //删除标志
	    
	    private Integer noCache; //缓存=0，不缓存=1
	    
	    private Integer hidden;  //隐藏 =1 ，不隐藏=0
	    
	    private String componentName; //组件名称

	    // 非数据库字段
	    private String parentName;
	    // 非数据库字段
	    private Integer level;
	    // 非数据库字段
	    private List<SysMenu> children;
	    
		public Long getParentId() {
			return parentId;
		}

		public void setParentId(Long parentId) {
			this.parentId = parentId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
		
		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getPerms() {
			return perms;
		}

		public void setPerms(String perms) {
			this.perms = perms;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}
		
		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

		public Integer getOrderNum() {
			return orderNum;
		}

		public void setOrderNum(Integer orderNum) {
			this.orderNum = orderNum;
		}

		public Byte getDelFlag() {
			return delFlag;
		}

		public void setDelFlag(Byte delFlag) {
			this.delFlag = delFlag;
		}

		public List<SysMenu> getChildren() {
			return children;
		}

		public void setChildren(List<SysMenu> children) {
			this.children = children;
		}

		public Integer getLevel() {
			return level;
		}

		public void setLevel(Integer level) {
			this.level = level;
		}

		public String getParentName() {
			return parentName;
		}

		public void setParentName(String parentName) {
			this.parentName = parentName;
		}
		
		public Integer getNoCache() {
			return noCache;
		}

		public void setNoCache(Integer noCache) {
			this.noCache = noCache;
		}
		
		public Integer getHidden() {
			return hidden;
		}

		public void setHidden(Integer hidden) {
			this.type = hidden;
		}
		
		public String getComponentName() {
			return componentName;
		}

		public void setComponentName(String componentName) {
			this.componentName = componentName;
		}
		

}
