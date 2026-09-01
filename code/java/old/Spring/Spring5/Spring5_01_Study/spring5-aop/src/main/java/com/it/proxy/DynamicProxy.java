package com.it.proxy;


import java.lang.reflect.Proxy;
import java.util.Arrays;

//@Component
public class DynamicProxy {
    public static Object getProxyInstance(Object target) {
       return Proxy.newProxyInstance(target.getClass().getClassLoader(),
               target.getClass().getInterfaces(),
                (proxy, method, args)->{
                    String methodName = method.getName();
                    System.out.println("动态代理-----[日志]:"+"["+methodName+"]"+"开始执行, 参数->>"+ Arrays.toString(args));
                    Object res = method.invoke(target, args);
                    System.out.println("动态代理-----[日志]:"+"["+methodName+"]"+"结束执行, 参数->>"+ Arrays.toString(args));
                    return res;
                });
    }


}
