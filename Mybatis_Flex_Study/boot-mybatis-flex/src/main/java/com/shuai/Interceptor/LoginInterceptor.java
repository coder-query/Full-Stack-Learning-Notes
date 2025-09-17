package com.shuai.Interceptor;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.InvocationHandler;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/7/31 0031
 */
public class LoginInterceptor implements InvocationHandler {
  @Override
  public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
    return null;
  }
}
