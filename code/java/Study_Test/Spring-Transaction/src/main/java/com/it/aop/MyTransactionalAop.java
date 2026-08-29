package com.it.aop;

import com.it.utils.TransactionalUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import javax.annotation.Resource;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/9 星期三 10:48
 */

@Component
@Aspect
public class MyTransactionalAop {

    @Resource
    private TransactionalUtils transactionalUtils;

    @Pointcut("execution(* com.it.service..*.*(..)) && @annotation(com.it.annotation.MyTransactional)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint joinPoint) {


        TransactionStatus transactionStatus = null;

        try {
            System.out.println("aop拦截事务启动...");
            /// 开启事务
            transactionStatus = transactionalUtils.begin();
            joinPoint.proceed();  // 执行目标方法
            /// 没有发生异常则提交事务
            transactionalUtils.commit(transactionStatus);
        } catch (Throwable e) {
            /// 捕获到异常,回滚事务
            if (transactionStatus != null) {
                transactionalUtils.rollback(transactionStatus);
            }
            e.printStackTrace();
        } finally {
            if (transactionStatus != null)
                transactionalUtils = null; /// 手动将它设为不可达
            System.out.println("aop拦截事务结束...");
        }

    }
}
