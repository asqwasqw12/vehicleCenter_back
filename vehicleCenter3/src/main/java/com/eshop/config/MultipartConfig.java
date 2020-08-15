package com.eshop.config;

import java.io.File;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

@Configuration
public class MultipartConfig {
	 @Bean
	 MultipartConfigElement multipartConfigElement() {
	        MultipartConfigFactory factory = new MultipartConfigFactory();
	      //单个文件最大100MB	        
	       factory.setMaxFileSize(DataSize.of(100, DataUnit.MEGABYTES)); 
	        /// 设置总上传数据总大小100MB
	        factory.setMaxRequestSize(DataSize.of(100, DataUnit.MEGABYTES));
		/*
		 * return factory.createMultipartConfig(); String location =
		 * System.getProperty("user.home") + "/.eladmin/file/tmp"; File tmpFile = new
		 * File(location); if (!tmpFile.exists()) { tmpFile.mkdirs(); }
		 * factory.setLocation(location); return factory.createMultipartConfig();
		 */
	        return factory.createMultipartConfig();
	    }

}
