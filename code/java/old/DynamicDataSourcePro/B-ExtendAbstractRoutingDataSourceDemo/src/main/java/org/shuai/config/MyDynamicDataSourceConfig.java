package org.shuai.config;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.shuai.constants.DataSourceConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

import java.util.LinkedHashMap;
import java.util.logging.Logger;


/**
 * 关键参数:
 *    1. targetDataSources   当前系统重的所有的数据源
 *    2. resolvedDataSources
 *    3. defaultTargetDataSource
 */

@Configuration
@Primary
@Slf4j
public class MyDynamicDataSourceConfig extends AbstractRoutingDataSource {

    public  static final ThreadLocal<String> dataSourceKey = new ThreadLocal<>();

    @Resource(name = DataSourceConstant.MYSQL_MASTER) private DataSource mysqlMasterDataSource;
    @Resource(name =DataSourceConstant.MYSQL_SLAVE) private DataSource mysqlSlaveDataSource;

    // 返回当前数据源标识
    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }

    @Override
    public void afterPropertiesSet() {
        LinkedHashMap<Object, Object> targetDataSourcesMap = new LinkedHashMap<>();
        targetDataSourcesMap.put(DataSourceConstant.MYSQL_MASTER, mysqlMasterDataSource);
        targetDataSourcesMap.put(DataSourceConstant.MYSQL_SLAVE, mysqlSlaveDataSource);
        // 初始化所有的数据源
        super.setTargetDataSources(targetDataSourcesMap);

        // 设置默认数据源
        super.setDefaultTargetDataSource(mysqlMasterDataSource);

        // 调用父类的afterPropertiesSet方法，内部会将targetDataSources赋值给resolvedDataSources
        super.afterPropertiesSet();
    }
}
