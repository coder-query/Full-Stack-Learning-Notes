package com.anxiang.mybatisspring.config;

import com.anxiang.mybatisspring.mapper.GoodsMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 类功能：
 *
 * @author shuaihong-coding
 * @date 2026-01-14 19:00
 */
@Component
public class ZshFactoryBean implements FactoryBean {

    private Class<?> interfaceName;

    public void setInterfaceName(Class<?> interfaceName) {
        this.interfaceName = interfaceName;
    }
    private SqlSession sqlSession;

    @Autowired
    public void setSqlSession(SqlSessionFactory sqlSessionFactory) {
        sqlSessionFactory.getConfiguration().addMapper(interfaceName);
        this.sqlSession = sqlSessionFactory.openSession();
    }

    @Override
    public Object getObject() throws Exception {
        return sqlSession.getMapper(interfaceName);
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceName;
//        return GoodsMapper.class;
    }
}
