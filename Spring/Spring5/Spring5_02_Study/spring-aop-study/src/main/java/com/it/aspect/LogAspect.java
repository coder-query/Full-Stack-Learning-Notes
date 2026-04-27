package com.it.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/8 星期二 22:46
 */
@Aspect
@Component
public class LogAspect {

	/// 定义切点 execution Pointcut
	@Pointcut("execution(* com.it.service..*.*(..))")
	public void pointCut() {
	}

	/// 定义切点 target Pointcut
	@Pointcut("target(com.it.service.Impl.UserLoginServiceImpl)")
	public void pointCutTarget() {
	}

	/// 定义切点 this Pointcut
	@Pointcut("this(com.it.service.Impl.UserLoginServiceImpl)")
	public void pointCutThis() {
	}

//	@Before("pointCutTarget()")
//	public void loginBefore(JoinPoint joinPoint) {
//		System.out.println("---------------------!.....登录前验证....!--------------------------");
//	}


	@Around("pointCut()")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("---------------------!.....登录前验证....!--------------------------");
		joinPoint.proceed();
		System.out.println("---------------------!.....结束....!--------------------------");
	}

}
