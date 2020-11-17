package com.koabs.web.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.koabs.util.ASCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private final static Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    @Value("${rpt.jdbc.driverClassName}")
    private String driverClassName;
    @Value("${rpt.jdbc.url}")
    private String url;
    @Value("${rpt.jdbc.username}")
    private String username;
    @Value("${rpt.jdbc.password}")
    private String password;
    @Value("${rpt.jdbc.minIdle}")
    private Integer minIdle;
    @Value("${rpt.jdbc.maxActive}")
    private Integer maxActive;

    @Bean(name = "dataSource")
    public DataSource configDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        try {
            dataSource.setUrl(url);
            dataSource.setUsername(ASCUtil.decryption(username));
            dataSource.setPassword(ASCUtil.decryption(password));
            dataSource.setInitialSize(1);
            dataSource.setMinIdle(minIdle);
            dataSource.setMaxActive(maxActive);
            dataSource.setMaxWait(60000);
            dataSource.setTimeBetweenEvictionRunsMillis(30000);
            dataSource.setMinEvictableIdleTimeMillis(300000);
            dataSource.setValidationQuery("SELECT 'x'");
            dataSource.setTestWhileIdle(true);
            dataSource.setTestOnBorrow(true);
            dataSource.setTestOnReturn(false);
            dataSource.setPoolPreparedStatements(false);
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
            dataSource.setFilters("stat");
        } catch (Exception e) {
            log.error("datasource Initialization produce error..");
        }
        return dataSource;
    }

}
