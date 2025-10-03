package com.it.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author shuaihong-coding
 * @date 2025/10/3
 * @className CglibDynamicAssistantProxy
 */
public class CglibDynamicAssistantProxy {
    public static Object getProxyInstance(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                String methodName = method.getName();
                System.out.println("cglib动态代理-----[日志]:" + "[" + methodName + "]" + "开始执行, 参数->>" + Arrays.toString(objects));
                Object res = method.invoke(target, objects);
                System.out.println("cglib动态代理-----[日志]:" + "[" + methodName + "]" + "结束执行, 参数->>" + Arrays.toString(objects));
                return res;
            }
        });
        return enhancer.create();
    }
}
