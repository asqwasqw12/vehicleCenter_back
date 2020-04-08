package com.eshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${file.path}")
    private String path;

    @Value("${file.avatar}")
    private String avatar;
	
	//跨域配置
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")	// 允许跨域访问的路径
        .allowedOrigins("*")	// 允许跨域访问的源
        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")	// 允许请求方法
        .maxAge(168000)	// 预检间隔时间
        .allowedHeaders("*")  // 允许头部设置
        .allowCredentials(true);	// 是否发送cookie
    }

	
	//静态资源配置
	
	  @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
	  String avatarUtl = "file:" + avatar.replace("\\","/"); 
	  String pathUtl = "file:" + path.replace("\\","/");	
	  registry.addResourceHandler("/avatar/**").addResourceLocations(avatarUtl).setCachePeriod(0);
	  registry.addResourceHandler("/file/**").addResourceLocations(pathUtl).setCachePeriod(0); 
	  registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/").setCachePeriod(0);
	  }
	 
	
	
}
