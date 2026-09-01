package com.it.config;

import com.it.Interceptor.ControllerHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcWebConfig implements WebMvcConfigurer {

    /**
     * 全局拦截所有Controller层的请求
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将拦截器添加到Springmvc环境,默认拦截所有Springmvc分发的请求
        registry.addInterceptor(new ControllerHandlerInterceptor());
    }


    /**
     * 精准拦截 指定Controller层请求
     */

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        //将拦截器添加到Springmvc环境,默认拦截所有Springmvc分发的请求
//        registry.addInterceptor(new ControllerHandlerInterceptor());
//
//        //精准匹配,设置拦截器处理指定请求 路径可以设置一个或者多个,为项目下路径即可
//        //addPathPatterns("/common/request/one") 添加拦截路径
//        //也支持 /* 和 /** 模糊路径。 * 任意一层字符串 ** 任意层 任意字符串
//        registry.addInterceptor(new ControllerHandlerInterceptor)
//        .addPathPatterns("/common/request/one","/common/request/tow");
//    }


    /**
     * 排除哪些请求不需要拦截
     */
    //添加拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        //将拦截器添加到Springmvc环境,默认拦截所有Springmvc分发的请求
//        registry.addInterceptor(new ControllerHandlerInterceptor());
//
//        //精准匹配,设置拦截器处理指定请求 路径可以设置一个或者多个,为项目下路径即可
//        //addPathPatterns("/common/request/one") 添加拦截路径
//        registry.addInterceptor(new ControllerHandlerInterceptor())
//                .addPathPatterns("/common/request/one", "/common/request/tow");
//
//
//        //排除匹配,排除应该在匹配的范围内排除
//        //addPathPatterns("/common/request/one") 添加拦截路径
//        //excludePathPatterns("/common/request/tow"); 排除路径,排除应该在拦截的范围内
//        registry.addInterceptor(new ControllerHandlerInterceptor())
//                .addPathPatterns("/common/request/one", "/common/request/tow")
//                .excludePathPatterns("/common/request/tow");
//    }
}
