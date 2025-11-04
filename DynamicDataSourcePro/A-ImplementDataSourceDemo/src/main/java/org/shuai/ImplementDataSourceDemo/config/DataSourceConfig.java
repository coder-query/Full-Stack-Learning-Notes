package org.shuai.ImplementDataSourceDemo.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.shuai.ImplementDataSourceDemo.constants.DataSourceConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean(name = DataSourceConstant.MYSQL_MASTER)
    @ConfigurationProperties(prefix = "spring.datasource.mysql.master")
    public DataSource mysqlMasterDataSource() {
        return new DruidDataSource();
    }
    @Bean(name = DataSourceConstant.MYSQL_SLAVE)
    @ConfigurationProperties(prefix = "spring.datasource.mysql.slave")
    public DataSource mysqlSlaveDataSource() {
        return new DruidDataSource();
    }
}
