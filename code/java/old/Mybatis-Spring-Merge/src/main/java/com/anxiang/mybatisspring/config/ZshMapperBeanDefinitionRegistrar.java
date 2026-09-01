package com.anxiang.mybatisspring.config;

import com.anxiang.mybatisspring.annotation.ZshMapperScan;
import com.anxiang.mybatisspring.mapper.GoodsMapper;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * 类功能：
 *
 * @author shuaihong-coding
 * @date 2026-01-14 18:57
 */
@Component
public class ZshMapperBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(ZshMapperScan.class.getName());
        String scanBasePackagePath = (String) annotationAttributes.get("value");
        System.out.println("ZshMapperBeanDefinitionRegistrar ---> scanBasePackagePath = " + scanBasePackagePath);
        ZshClassScanner zshClassScanner = new ZshClassScanner(registry);
        zshClassScanner.addIncludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                return true;
            }
        });

        zshClassScanner.scan(scanBasePackagePath);


//        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(GoodsMapper.class).getBeanDefinition();
//        registry.registerBeanDefinition("goodsMapper", beanDefinition);
    }

}
