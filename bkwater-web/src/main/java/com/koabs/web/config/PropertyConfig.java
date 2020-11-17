package com.koabs.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * Author: koabs
 * 2020/7/25.
 */
@Configuration
public class PropertyConfig {

    private static final Logger logger = LoggerFactory.getLogger(PropertyConfigurer.class);

    public PropertyConfig() {}

    @Bean
    public static PropertyConfigurer myPropertySourcesPlaceholderConfigurer() {

        PropertyConfigurer propertyConfigurer =  new PropertyConfigurer();
        String location = System.getProperty("UHOME_CONFIG_PATH");

        logger.info("从系统属性加载 UHOME_CONFIG_PATH:" + location);

        if (StringUtils.isEmpty(location)) {
            location = System.getenv("UHOME_CONFIG_PATH");
            logger.info("从环境变量加载 UHOME_CONFIG_PATH:" + location);
        }

        propertyConfigurer.setLocationNames(new String[] {location});
        return propertyConfigurer;
    }

}
