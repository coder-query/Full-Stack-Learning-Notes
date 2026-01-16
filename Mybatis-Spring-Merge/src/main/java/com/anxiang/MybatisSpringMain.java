package com.anxiang;

import com.anxiang.mybatisspring.config.SpringBeanConfig;
import com.anxiang.mybatisspring.service.BusinessService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MybatisSpringMain {
    public static void main(String[] args) {
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
//        System.out.println(goodsMapper.selectAll());

        ApplicationContext IOC =
                new AnnotationConfigApplicationContext(SpringBeanConfig.class);
        BusinessService businessService = IOC.getBean(BusinessService.class);
        System.out.println(businessService.getAllGoods());
    }
}