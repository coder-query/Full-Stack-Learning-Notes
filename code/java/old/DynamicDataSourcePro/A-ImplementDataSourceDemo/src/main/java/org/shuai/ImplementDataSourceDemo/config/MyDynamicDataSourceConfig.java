package org.shuai.ImplementDataSourceDemo.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.shuai.ImplementDataSourceDemo.constants.DataSourceConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;


@Configuration
@Primary
@Slf4j
public class MyDynamicDataSourceConfig implements DataSource {

    public  static final ThreadLocal<String> dataSourceKey = new ThreadLocal<>();

    @Resource(name = DataSourceConstant.MYSQL_MASTER) private DataSource mysqlMasterDataSource;
    @Resource(name =DataSourceConstant.MYSQL_SLAVE) private DataSource mysqlSlaveDataSource;

    @PostConstruct
    public void setDefaultTargetDataSource() {
        // 设置默认数据源标识
        dataSourceKey.set(DataSourceConstant.MYSQL_MASTER);
    }

    @Override
    public Connection getConnection() throws SQLException {
      if (dataSourceKey.get().equals(DataSourceConstant.MYSQL_MASTER)){
          log.info("使用主数据源");
          return mysqlMasterDataSource.getConnection();
      }else {
          log.info("使用从数据源");
          return mysqlSlaveDataSource.getConnection();
      }
    }

    @Override
    public Connection getConnection(String s, String s1) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter printWriter) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int i) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return false;
    }
}
