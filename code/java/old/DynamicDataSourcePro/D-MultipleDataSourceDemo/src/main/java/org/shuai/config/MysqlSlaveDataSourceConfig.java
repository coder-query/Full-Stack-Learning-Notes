package org.shuai.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(
        basePackages = {"org.shuai.**.mapper.slave"},  // 从库mapper路径
        sqlSessionTemplateRef = MysqlSlaveDataSourceConfig.MysqlSlaveSqlSessionTemplate
)
public class MysqlSlaveDataSourceConfig {

    // 从库常量定义（与主库区分）
    public static final String MysqlSlaveDataSource = "mysqlSlaveDataSource";
    public static final String MysqlSlaveTransactionManager = "mysqlSlaveTransactionManager";
    public static final String MysqlSlaveSqlSessionFactory = "mysqlSlaveSqlSessionFactory";
    public static final String MysqlSlaveSqlSessionTemplate = "mysqlSlaveSqlSessionTemplate";
    // 从库mapper.xml路径（建议与主库分目录，如：mybatis/mysql/slave/**/*.xml）
    public static final String MysqlSlaveMapperLocations = "classpath*:mapper/slave/**/*.xml";


    // 1. 从库数据源（不标记@Primary，避免与主库冲突）
    @Bean(name = MysqlSlaveDataSource)
    @ConfigurationProperties(prefix = "spring.datasource.mysql.slave")  // 绑定从库配置前缀
    public DataSource mysqlSlaveDataSource() {
        return new DruidDataSource();  // 复用Druid连接池
    }


    // 2. 从库SqlSessionFactory（复用主库的MybatisPlusInterceptor，无需重复定义）
    @Bean(name = MysqlSlaveSqlSessionFactory)
    public MybatisSqlSessionFactoryBean mysqlSlaveSqlSessionFactory(
            @Qualifier(MysqlSlaveDataSource) DataSource dataSource,
            // 注入主库定义的MybatisPlusInterceptor（分页插件等通用）
            @Qualifier("mybatisPlusInterceptor") MybatisPlusInterceptor mybatisPlusInterceptor) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);  // 关联从库数据源
        // 扫描从库mapper.xml（需与主库分目录，避免冲突）
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MysqlSlaveMapperLocations));
        bean.setPlugins(mybatisPlusInterceptor);  // 复用拦截器（分页等功能生效）

        // MyBatis基础配置（与主库保持一致，统一规范）
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);  // 打印SQL日志
        configuration.setMapUnderscoreToCamelCase(true);  // 驼峰命名转换
        configuration.setCacheEnabled(false);  // 禁用二级缓存
        bean.setConfiguration(configuration);
        return bean;
    }


    // 3. 从库事务管理器（管理从库事务）
    @Bean(name = MysqlSlaveTransactionManager)
    public DataSourceTransactionManager mysqlSlaveTransactionManager(
            @Qualifier(MysqlSlaveDataSource) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    // 4. 从库SqlSessionTemplate（执行从库SQL的入口）
    @Bean(name = MysqlSlaveSqlSessionTemplate)
    public SqlSessionTemplate mysqlSlaveSqlSessionTemplate(
            @Qualifier(MysqlSlaveSqlSessionFactory) SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
