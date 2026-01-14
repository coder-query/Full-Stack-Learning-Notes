package com.anxiang.mybatisspring.config;

import com.anxiang.mybatisspring.mapper.GoodsMapper;
import org.springframework.beans.factory.FactoryBean;
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

//    private Class<?> interfaceName;
//
//    public void setInterfaceName(Class<?> interfaceName) {
//        this.interfaceName = interfaceName;
//    }

    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(GoodsMapper.class.getClassLoader(), new Class[]{GoodsMapper.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("这里输出了" + method.getName());
                return null;
            }
        });
    }

    @Override
    public Class<?> getObjectType() {
//        return interfaceName;
        return GoodsMapper.class;
    }
}
