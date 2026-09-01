package com.it.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.it..*.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void before(){
        System.out.println("spring aop 动态代理 -> 日志输出----before");
    }


    @After("pointCut()")
    public void after(){
        System.out.println("spring aop 动态代理 -> 日志输出----after");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("spring aop 动态代理 -> 日志输出----afterThrowing");
    }

    @AfterReturning("pointCut()")
    public void afterReturning(){
        System.out.println("spring aop 动态代理 -> 日志输出----afterReturning");
    }


    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Long time_01 = System.currentTimeMillis();
        String name = joinPoint.getSignature().getName();
        Object proceed = joinPoint.proceed();
        Long time_02 = System.currentTimeMillis();
        System.out.println(name+"spring aop 动态代理 -> 方法耗时-->：" + (time_02 - time_01));
        return proceed;
    }
}
