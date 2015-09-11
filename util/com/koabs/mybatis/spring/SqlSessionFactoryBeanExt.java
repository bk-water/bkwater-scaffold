package com.koabs.mybatis.spring;

import static org.springframework.util.Assert.notNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.core.io.Resource;
import org.w3c.dom.Document;

import com.koabs.mybatis.util.ClassPathScan;

public class SqlSessionFactoryBeanExt extends SqlSessionFactoryBean{
	
	  private String basePackge;
	  private String dao;
	  private String sqlProvider;
	
	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public void afterPropertiesSet() throws Exception {
	    notNull("pageLocation");
	   super.afterPropertiesSet();
	  }
	  
	  /**
	   * 在Mybatis 解析前生成通用Mapper.xml 加到现有的xml中.
	   */
	  @Override
	  protected Resource[] wrapperMapperLocation(Resource[] mapperLocations){
		  // 获取 basePackge下所有 IDAO 的实现类
		 Map<String, Object> map = ClassPathScan.getClassMap(basePackge, dao);
		  
		  // 根据获取的IDao 来生成Xml对象
		  return mapperLocations;
	  }
	  
	 
}
