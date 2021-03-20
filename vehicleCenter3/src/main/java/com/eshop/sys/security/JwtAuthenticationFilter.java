package com.eshop.sys.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.eshop.common.SecurityUtils;

import lombok.extern.slf4j.Slf4j;




//登录认证过滤器
@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    
	//@Autowired  
	//@Qualifier(value = "userDetailsServiceImpl")
	@Resource(name="userDetailsServiceImpl")
	private  UserDetailsService userDetailsService; //用户信息service
	
	@Autowired
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    	// 获取token, 并检查登录状态
    	log.info("登录开始过滤...");
        SecurityUtils.checkAuthentication(request);
        chain.doFilter(request, response);
        log.info("过滤程序执行完毕...");    
   }
}
