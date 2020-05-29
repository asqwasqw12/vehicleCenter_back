package com.eshop.sys.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;

import com.eshop.sys.pojo.SysUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

//安全用户模型
@Getter
@AllArgsConstructor
public class JwtUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
    private  Long id;
	private String username;
	@JsonIgnore
    private String password;
    private String salt;  
    private  String realName;
    private  String nickName;
    private  String avatar;
    private  String email;
    private  String mobile;
    private  Long   deptId;
    private String deptName;
    private String job;
    private String roleNames;
    private Date createTime;
    private Collection<? extends GrantedAuthority> authorities;

	
	  JwtUserDetails(String username, String password, String salt, Collection<?extends GrantedAuthority> authorities) { 
		  this.username = username;
		  this.password = password; 
		  this.salt = salt; 
		  this.authorities = authorities; 
		  }
	  
	    JwtUserDetails(SysUser user, Collection<? extends GrantedAuthority> authorities){
	    	this.username = user.getName();
	    	this.password = user.getPassword();
	    	this.salt = user.getSalt();
	    	this.deptName = user.getDeptName();
	    	this.job = user.getJob();
	    	this.realName = user.getRealName();
	    	this.authorities = authorities; 
	    }

    
    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore			//在实体类向前台返回数据时用来忽略不想传递给前台的属性或接口。
    @Override
    public String getPassword() {
        return password;
    }

    public String getSalt() {
		return salt;
	}
    
    public String getDeptName() {
    	return deptName;
    }
    
    public String getJob() {
    	return job;
    }
    
    public String getRealName() {
    	return realName;
    }
    
    //获取当前用户对象所具有的角色信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    //账号未过期
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账户未锁定
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //账户密码未过期
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //账号可用
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
