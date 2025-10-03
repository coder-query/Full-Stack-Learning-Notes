package com.it.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/12 星期六 2:07
 */
@Component
@Aspect
public class LogAspect {
	@Pointcut("execution(* com.it.service..*.*(..))")
	public void pointcut() {
	}


	@Before("pointcut()")
	public void beforeMeeting(JoinPoint joinPoint) {
		System.out.println("spring aop-----[日志]");
		System.out.println("我是老板秘书,我们老板有事晚点到,各位大佬请喝茶....");
	}

	@After("pointcut()")
	public void afterMeeting(JoinPoint joinPoint) {
		System.out.println("spring aop-----[日志]");
		System.out.println("我是老板秘书,现在没我事了,我先走了哈,你们开会...");
	}

//	@Around("pointcut()")
//	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
//		System.out.println("我是老板秘书,我们老板有事晚点到,各位大佬请喝茶....");
//		joinPoint.proceed();
//		System.out.println("我是老板秘书,我们老板有事晚点到,各位大佬请喝茶....");
//	}

}
