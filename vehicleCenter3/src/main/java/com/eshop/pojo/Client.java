package com.eshop.pojo;

import java.util.Date;

public class Client extends BasePojo {
	
	private String name;		//客户姓名
	private String sex;		     //性别
	private String mobilephone;		//手机号
	private String telephone;		//座机
	private String email;			//邮箱
	private String post;			//职务
	private String qq;				//QQ
	private String wechat;			//微信
	private String education;		//教育程度
	private String hobby;			//爱好
	private String address;			//联系地址
	private Date  birthday;			//生日
	private String constellation;   //星座
	private Long  upperContactId;	//上级联系人Id
	private Long  companyId;		//公司Id
	private String remarks;			//备注
	//非数据库字段
	private String upperContactName; //上级联系人
	//非数据库字段
	private String companyName;		//公司名称
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthdays(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	
	public Long getUpperContactId() {
		return upperContactId;
	}
	
	public void setUpperContactId(Long upperContactId) {
		this.upperContactId = upperContactId;
	}
	
	public Long getCompanyId() {
		return companyId;
	}
	
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	public String getUpperContactName() {
		return upperContactName;
	}
	public void setUpperContactName(String upperContactName) {
		this.upperContactName = upperContactName;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
