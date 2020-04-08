package com.eshop.sys.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.common.HttpResult;
import com.eshop.common.PasswordUtils;
import com.eshop.common.SecurityUtils;
import com.eshop.sys.pojo.LoginBean;
import com.eshop.sys.pojo.SysUser;
import com.eshop.sys.security.JwtAuthenticatioToken;
import com.eshop.sys.security.JwtUserDetails;
import com.eshop.sys.service.SysUserService;



@RestController
public class SysLoginController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping(value = "/login")
	public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
		String username = loginBean.getAccount();
		String password = loginBean.getPassword();
		System.out.println("Account:"+username+"password:"+password);
		// 用户信息
		SysUser user = sysUserService.findByName(username);
		// 账号不存在、密码错误
		if (user == null) {
			return HttpResult.error("账号不存在");
		}
		if (!PasswordUtils.matches(user.getSalt(), password, user.getPassword())) {
			return HttpResult.error("密码不正确");
		}
		
		// 账号锁定
		if (user.getStatus() == 0) {
			return HttpResult.error("账号已被锁定,请联系管理员");
		}
		System.out.println("开始系统登录认证。。。");
		// 系统登录认证
		JwtAuthenticatioToken token = SecurityUtils.login(request, username, password, authenticationManager);
		System.out.println("系统登录认证结束！！！");
		return HttpResult.ok(token);
	}

	@GetMapping("/getInfo")
	public HttpResult getInfo() {
		String userName = SecurityUtils.getUsername();
		System.out.println("用户名："+userName);
		if(userName != null)
		{
		JwtUserDetails jwtUser =  (JwtUserDetails) userDetailsService.loadUserByUsername(userName);
		return HttpResult.ok(jwtUser);
		}else {
			return HttpResult.error("用户令牌过期或者错误");
		}
	}
	
}
