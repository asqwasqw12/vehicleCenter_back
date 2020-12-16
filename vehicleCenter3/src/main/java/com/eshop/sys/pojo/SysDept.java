package com.eshop.sys.pojo;

import java.math.BigDecimal;
//import java.util.Date;
import java.sql.Date;
import java.util.List;

import com.eshop.pojo.Client;


public class SysDept extends BaseModel {
	
    private String name;       //单位名称   
    private Long parentId;     //父Id   
    private String address;		//办公地址
	private String telephone;	//座机
	private String website;		//网址
	private String industry;	//行业
	private String remarks;		//备注
    private Integer orderNum;   //排序
    private Integer isCompany;	//公司或部门
    private Byte delFlag;		//删除标记
    private Integer personNum;  //环卫人员数量
    private Integer serviceNum;	//服务人口（万）
    private Integer cleanArea;  //清扫保洁面积(万平方米）
    private Integer greenArea;	//绿化带面积(万平方米）
    private Float machineRate;	//机械化作业率
    private Float rubbishVolume;//垃圾清运量（吨/天）
    private Float renewableResourcesVolume;//可再生资源回收（吨/月）
    private java.sql.Date workStartTime;		//进场时间 java.sql.Date类
    private String administrativeDivision;	//行政区域
    private Float latitude;		//经度
    private Float longitude;	//纬度
    private String juridicalPerson;//公司法人
    private java.sql.Date registeredTime;	//注册时间 java.sql.Date类
    private BigDecimal registeredCapital; //注册资金
    private String stockRate;   //持股说明
    private String manager;		//总经理
    private String managerPhone;//总经理电话
    private String viceManager; //副总经理
    private String viceManagerPhone;//副总经理电话
    private String fax; //传真号
    
    // 非数据库字段
    private List<SysDept> children;
    // 非数据库字段
    private String parentName;
    // 非数据库字段
    private Integer level;
    //非数据库字段
    private Client client;
    //非数据库字段
    private SysUser sysUser;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public List<SysDept> getChildren() {
		return children;
	}
	public void setChildren(List<SysDept> children) {
		this.children = children;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	public Integer getIsCompany() {
		return isCompany;
	}
	
	public void setIsCompany(Integer isCompany) {
		this.isCompany = isCompany;
	}
	
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Integer getPersonNum() {
		return personNum;
	}
	
	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}
	
	public Integer getServiceNum() {
		return serviceNum;
	}
	
	public void setServiceNum(Integer serviceNum) {
		this.serviceNum = serviceNum;
	}
  
	public Integer getCleanArea() {
		return cleanArea;
	}
	
	public void setCleanArea(Integer cleanArea) {
		this.cleanArea = cleanArea;
	}
	
	public Integer getGreenArea() {
		return greenArea;
	}
	
	public void setGreenArea(Integer greenArea) {
		this.greenArea = greenArea;
	}
	
	public Float getMachineRate() {
		return machineRate;
	}
	
	public void setMachineRate(Float machineRate) {
		this.machineRate = machineRate;
	}
	
	public Float getRubbishVolume() {
		return rubbishVolume;
	}
	
	public void setRubbishVolume(Float rubbishVolume) {
		this.rubbishVolume = rubbishVolume;
	}
	
	public Float getRenewableResourcesVolume() {
		return renewableResourcesVolume;
	}
	
	public void setRenewableResourcesVolume(Float renewableResourcesVolume) {
		this.renewableResourcesVolume = renewableResourcesVolume;
	}
	
	public Date getWorkStartTime() {
		return workStartTime;
	}
	
	public void setWorkStartTime(Date workStartTime) {
		this.workStartTime = workStartTime;
	}
	
	public String getAdministrativeDivision() {
		return administrativeDivision;
	}
	public void setAdministrativeDivision(String administrativeDivision) {
		this.administrativeDivision = administrativeDivision;
	}
	
	public Float getLatitude() {
		return latitude;
	}
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	
	public Float getLongitude() {
		return longitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	
	
	public String getJuridicalPerson() {
		return juridicalPerson;
	}
	public void setJuridicalPerson(String juridicalPerson) {
		this.juridicalPerson = juridicalPerson;
	}
	
	
	public Date getRegisteredTime() {
		return registeredTime;
	}
	
	public void setRegisteredTime(Date registeredTime) {
		this.registeredTime = registeredTime;
	}
	
	public BigDecimal getRegisteredCapital() {
		return registeredCapital;
	}
	
	public void setRegisteredCapital(BigDecimal registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
	
	public String getStockRate() {
		return stockRate;
	}
	public void setStockRate(String stockRate) {
		this.stockRate = stockRate;
	}
	
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	
	public String getViceManager() {
		return viceManager;
	}
	public void setViceManager(String viceManager) {
		this.viceManager = viceManager;
	}
	
	public String getViceManagerPhone() {
		return viceManagerPhone;
	}
	public void setViceManagerPhone(String viceManagerPhone) {
		this.viceManagerPhone = viceManagerPhone;
	}
	
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
}
