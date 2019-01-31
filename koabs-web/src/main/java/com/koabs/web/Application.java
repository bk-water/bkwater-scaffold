package com.koabs.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = {JacksonAutoConfiguration.class, TaskSchedulingAutoConfiguration.class})
@Slf4j
public class Application extends SpringBootServletInitializer {

	/**
	 *   切换成 war 打包模式时
	 *   需排除 pom 中spring-boot-starter-tomcat 依赖 改成 provided 方式
	 * @param builder
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		log.info("====== Tomcat 外部服务器启动加载成功 ======");
		return builder.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		log.info("====== APP Jar 服务启动成功 ======");
	}

}

