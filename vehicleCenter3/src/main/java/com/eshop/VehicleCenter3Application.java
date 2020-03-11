package com.eshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@MapperScan(basePackages = {"com.eshop.dao","com.eshop.jt808.dao"}),如果在MybatisConfig里面没有配置就把这行加上
@SpringBootApplication
public class VehicleCenter3Application {

	public static void main(String[] args) {
		SpringApplication.run(VehicleCenter3Application.class, args);
	}

}
