package com.anxiang.mybatisspring.annotation;

import com.anxiang.mybatisspring.config.ZshMapperBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类功能：
 *
 * @author shuaihong-coding
 * @date 2026-01-14 18:52
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
@Import(ZshMapperBeanDefinitionRegistrar.class)
public @interface ZshMapperScan {
    String value() default "";
}
