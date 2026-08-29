package com.anxiang.mybatisspring.config;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/**
 * 类功能：
 *
 * @author shuaihong-coding
 * @date 2026-01-15 11:10
 */
public class ZshClassScanner extends ClassPathBeanDefinitionScanner {
    public ZshClassScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        beanDefinitionHolders.forEach(beanDefinitionHolder -> {
            BeanDefinition beanDefinition = beanDefinitionHolder.getBeanDefinition();
            System.out.println("ZshClassScanner ---> beanDefinition = " + beanDefinition);
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinition.getBeanClassName());
            System.out.println("ZshClassScanner ---> beanDefinition.getBeanClassName() = " + beanDefinition);
            beanDefinition.setBeanClassName(ZshFactoryBean.class.getName());
            System.out.println("ZshClassScanner ---> ZshFactoryBean.class.getName() = " + beanDefinition);
        });

        return beanDefinitionHolders;
    }
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        // 是接口就直接放行
        return beanDefinition.getMetadata().isInterface();
    }
}
