package com.eshop.sys.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eshop.aop.Log;
import com.eshop.common.FileUtil;
import com.eshop.common.HttpResult;
import com.eshop.common.PasswordUtils;
import com.eshop.common.SecurityUtils;
import com.eshop.sys.pojo.LoginBean;
import com.eshop.sys.pojo.SysUser;
import com.eshop.sys.security.JwtAuthenticatioToken;
import com.eshop.sys.security.JwtUserDetails;
import com.eshop.sys.service.OnlineUserService;
import com.eshop.sys.service.SysUserService;



@RestController
public class SysLoginController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	OnlineUserService onlineUserService;
	
	@Log("用户登录")
	@PostMapping(value = "/login")
	public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
		String path = System.getProperty("user.dir");
		System.out.println("path="+path);
		String path1 = ResourceUtils.getURL("classpath:").getPath();
		 String absolutePath =URLDecoder.decode(new File(path1).getAbsolutePath(),"utf-8") + File.separator;
		   String projectRootAbsolutePath = absolutePath;

	        int index = projectRootAbsolutePath.indexOf("file:");
	        System.out.println("index="+String.valueOf(index));
	        if (index != -1){
	            projectRootAbsolutePath = projectRootAbsolutePath.substring(0, index);
	        }

	        System.out.println("static="+projectRootAbsolutePath + "static" + File.separator);
		 System.out.println("absolutePath="+absolutePath);
		System.out.println("path1="+path1);
		String classPath = FileUtil.class.getClassLoader().getResource("").getPath();
		System.out.println("path1="+classPath);
		String path2 = new File(classPath).getParentFile().getParentFile().getAbsolutePath();
		System.out.println("path1="+path2);
		/*
		 * File upload = new File(path.getAbsolutePath(),"static/images/upload/");
		 * if(!upload.exists()) upload.mkdirs();
		 * System.out.println("upload url:"+upload.getAbsolutePath());
		 */
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
		String strToken = token.getToken();
		Authentication authentication=SecurityUtils.getAuthentication();
		if(authentication != null)
		{
			final JwtUserDetails jwtUser = (JwtUserDetails) authentication.getPrincipal();
			// 保存在线信息
	        onlineUserService.save(jwtUser, strToken, request);
		}
		
		System.out.println("系统登录认证结束！！！");
		return HttpResult.ok(token);
	}

	@Log("获取个人信息")
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
	
	@Log("用户注册")
	@PostMapping(value = "/register")
	public HttpResult saveRegisterInfo(@RequestBody SysUser record) {
		System.out.println("user:"+record);
		SysUser user = sysUserService.findByName(record.getName());
		if(user !=null) {
			return HttpResult.error("用户名已存在，请重新申请！");
		}else {
			String salt = PasswordUtils.getSalt();
			String password = PasswordUtils.encode(record.getPassword(), salt);
			record.setSalt(salt);
			record.setPassword(password);
			Byte status =2;				//设置用户状态为2，表示新用户
			record.setStatus(status);
			return HttpResult.ok(sysUserService.save(record));
		}
		
		
	}
}
