package org.shuai.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 事务使用：
 *      操作主库时，在 Service 方法上添加@Transactional(value = "mysqlMasterTransactionManager")；
 *      操作从库时，添加@Transactional(value = "mysqlSlaveTransactionManager")；
 *      （若不指定，默认使用@Primary标记的主库事务管理器）
 */
@Configuration
@MapperScan(basePackages = {"org.shuai.**.mapper.master"},
        sqlSessionTemplateRef = MysqlMasterDataSourceConfig.MysqlMasterSqlSessionTemplate
)
public class MysqlMasterDataSourceConfig {

    public static final String MysqlMasterDataSource = "mysqlMasterDataSource";
    public static final String MysqlMasterTransactionManager = "mysqlMasterTransactionManager";

    public static final String MysqlMasterSqlSessionFactory = "mysqlMasterSqlSessionFactory";

    public static final String MysqlMasterSqlSessionTemplate = "mysqlMasterSqlSessionTemplate";
    public static final String MysqlMasterMapperLocations = "classpath*:mapper/master/**/*.xml";


    // 1. 只配置 Druid 数据源（MyBatis-Plus 会自动使用这个数据源）
    @Bean(name = MysqlMasterDataSource)
    @ConfigurationProperties(prefix = "spring.datasource.mysql.master")
    @Primary
    public DataSource mysqlDataSource() {
        return new DruidDataSource();
    }

    // 2. 配置 MyBatis-Plus 核心拦截器（分页等功能必需）
    @Bean(name = "mybatisPlusInterceptor")
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件（指定数据库类型为 MySQL）
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 可按需添加其他插件（如乐观锁插件：new OptimisticLockerInnerInterceptor()）
        return interceptor;
    }

    // 3. 自定义 SqlSessionFactory（使用 MyBatis-Plus 增强版 Bean，注入数据源和拦截器）
    @Bean(name = MysqlMasterSqlSessionFactory)
    @Primary
    public MybatisSqlSessionFactoryBean mysqlMasterSqlSessionFactory(
            @Qualifier(MysqlMasterDataSource) DataSource dataSource,
            MybatisPlusInterceptor mybatisPlusInterceptor) throws Exception {
        // 关键：使用 MyBatis-Plus 的 MybatisSqlSessionFactoryBean，而非 MyBatis 原生的
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 扫描 Mapper.xml 文件
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MysqlMasterMapperLocations));
        // 注入 MyBatis-Plus 拦截器（分页等功能生效的核心）
        bean.setPlugins(mybatisPlusInterceptor);

        // 自定义 MyBatis 基础配置（保留不变）
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class); // 日志打印
        configuration.setMapUnderscoreToCamelCase(true); // 驼峰命名
        configuration.setCacheEnabled(false); // 禁用二级缓存
        bean.setConfiguration(configuration);
        return bean;
    }

    // 4. 事务管理器（使用自定义数据源）
    @Bean(name = MysqlMasterTransactionManager)
    @Primary
    public DataSourceTransactionManager mysqlMasterTransactionManager(
            @Qualifier(MysqlMasterDataSource) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    // 5. SqlSessionTemplate（复用上面的 SqlSessionFactory，自动继承 MyBatis-Plus 增强功能）
    @Bean(name = MysqlMasterSqlSessionTemplate)
    @Primary
    public SqlSessionTemplate mysqlMasterSqlSessionTemplate(
            @Qualifier(MysqlMasterSqlSessionFactory) SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
