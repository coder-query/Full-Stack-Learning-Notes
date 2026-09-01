package com.anxiang.mybatisspring.config;

import com.anxiang.mybatisspring.annotation.ZshMapperScan;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类功能：
 *
 * @author shuaihong-coding
 * @date 2026-01-14 18:51
 */
@Configuration
@ComponentScan(value = {"com.anxiang.mybatisspring"})
@ZshMapperScan(value = "com.anxiang.mybatisspring.mapper")
public class SpringBeanConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            return new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
