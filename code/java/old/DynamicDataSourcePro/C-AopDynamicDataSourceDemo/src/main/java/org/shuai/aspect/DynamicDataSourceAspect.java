package org.shuai.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.shuai.annotation.DBsource;
import org.shuai.config.MyDynamicDataSourceConfig;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class DynamicDataSourceAspect {

    // 前置通知
    @Before("within(org.shuai.controller.*) && @annotation(dBsource)")
    public void before(JoinPoint joinPoint, DBsource dBsource) {
        String value = dBsource.value();
        MyDynamicDataSourceConfig.dataSourceKey.set(value);
        log.info("数据源切换到：{}", value);
    }
}
