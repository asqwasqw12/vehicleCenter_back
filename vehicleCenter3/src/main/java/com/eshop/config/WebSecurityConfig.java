package com.eshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import com.eshop.sys.security.JwtAuthenticationFilter;
import com.eshop.sys.security.JwtAuthenticationProvider;

//spring-security配置
@Configuration
@EnableWebSecurity	// 开启Spring Security 
@EnableGlobalMethodSecurity(prePostEnabled = true)	// 开启权限注解，如：@PreAuthorize注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件
    	System.out.println("userDetailService="+userDetailsService);
        auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));
    }
    
    //加密
	/*
	 * @Bean public PasswordEncoder passwordEncoder() { // 密码加密方式 return new
	 * BCryptPasswordEncoder(); }
	 */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用 csrf, 由于使用的是JWT，我们这里不需要csrf
        http.cors().and().csrf().disable()
    		.authorizeRequests()
    		// 跨域预检请求
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            // 静态资源等等
            .antMatchers(
                    HttpMethod.GET,
                    "/*.html",
                    "/*.jpg",
                    "/avatar/**/*.jpg",
                    "/avatar/**/*.png",
                    "/avatar/**/*.gif",
                    "/avatar/**/*.bmp",
                    "/avatar/**/*.ico",
                    "/file/**",
                    "/**/*.html",
                    "/**/*.css",
                    "/**/*.js",
                    "/webSocket/**"
            ).permitAll()
            // web jars
            .antMatchers("/webjars/**").permitAll()
            // 查看SQL监控（druid）
            .antMatchers("/druid/**").permitAll()
            // 首页和登录页面
            .antMatchers("/").permitAll()
            .antMatchers("/login").permitAll()
            //注册
            .antMatchers("/register").permitAll()
            // swagger
            .antMatchers("/swagger-ui.html").permitAll()
            .antMatchers("/swagger-resources/**").permitAll()
            .antMatchers("/v2/api-docs").permitAll()
            .antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
            // 验证码
            .antMatchers("/captcha.jpg**").permitAll()
            // 服务监控
            .antMatchers("/actuator/**").permitAll()
            // 其他所有请求需要身份认证
            .anyRequest().authenticated();
        //// 防止iframe 造成跨域
        http.headers().frameOptions().disable();
        // 退出登录处理器
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        // token验证过滤器
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
    	return super.authenticationManager();
    }
    
}