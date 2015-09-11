package com.koabs.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取Spring 的ApplicationContext
 * 如需使用的话需要在 Spring 的配置文件中配置注入
 * @author kevin1
 *
 */
public class SpringApplicationUtils implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		 this.applicationContext = applicationContext;  	
	}
	
	public ApplicationContext getApplicationContext(){
		return this.applicationContext;
	}

}
