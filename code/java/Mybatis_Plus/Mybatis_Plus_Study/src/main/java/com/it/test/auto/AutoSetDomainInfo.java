package com.it.test.auto;

import com.it.test.entity.TestLimitEntity;
import com.it.test.mapper.TestLimitMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AutoSetDomainInfo implements CommandLineRunner {

    @Resource
    private TestLimitMapper testLimitMapper;

    @Override
    public void run(String... args) throws Exception {
        TestLimitEntity testLimit = new TestLimitEntity();
        testLimit.setName("张三");
        testLimit.setAge(18);

        int insert = testLimitMapper.insert(testLimit);
        System.out.println("插入数据：" + insert + "条");
    }
}
