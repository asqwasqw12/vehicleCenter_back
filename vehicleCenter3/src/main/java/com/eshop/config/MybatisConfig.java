package com.eshop.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = {"com.eshop.dao","com.eshop.jt808.dao","com.eshop.sys.dao","com.eshop.gateway.gb32960.dao"})  //扫描DAO
public class MybatisConfig {
  @Autowired
  private DataSource dataSource;

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    sessionFactory.setTypeAliasesPackage("com.eshop.**.pojo");    // 扫描pojo
    
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    //sessionFactory.setMapperLocations(resolver.getResources("classpath*:**/sqlmap/*.xml"));    // 扫描映射文件
    sessionFactory.setMapperLocations(resolver.getResources("classpath:resources/sqlmap/*.xml"));    // 扫描映射文件
    return sessionFactory.getObject();
  }
}