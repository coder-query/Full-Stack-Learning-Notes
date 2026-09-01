package org.example.mybatisbatchtest.controller;

import com.baomidou.mybatisplus.core.batch.MybatisBatch;
import org.example.mybatisbatchtest.Test;
import org.example.mybatisbatchtest.mapper.TestMapper;
import org.example.mybatisbatchtest.service.TestServiceImpl;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private TestServiceImpl testServiceImpl;

    @Resource
    private org.apache.ibatis.session.SqlSessionFactory sqlSessionFactory;
    @RequestMapping("/saveBatch")
    public String saveBatch() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 测试 Service 提供的 saveBatch 方法
        ArrayList<Test> list = new ArrayList<>();
        for (int i = 1; i <= 5000; i++) {
            Test test = new Test();
            test.setId((long) i);
            test.setName("测试" + i);
            list.add(test);
        }
        testServiceImpl.saveBatch(list);
        System.out.println("5000 saveBatch 保存成功");
        stopWatch.stop();
        return "saveBatch 耗时：" + stopWatch.getTotalTimeMillis();
    }

    @RequestMapping("/mybatisBatch")
    public String mybatisBatch() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ArrayList<Test> list = new ArrayList<>();
        for (int i = 1; i <= 5000; i++) {
            Test test = new Test();
            test.setId((long) i);
            test.setName("测试" + i);
            list.add(test);
        }
        MybatisBatch<Test> mybatisBatch = new MybatisBatch<>(sqlSessionFactory, list);
        MybatisBatch.Method<Test> method = new MybatisBatch.Method<>(TestMapper.class);
        mybatisBatch.execute(method.insert());
        System.out.println("5000 mybatisBatch 批量保存成功");
        stopWatch.stop();
        return "mybatisBatch 耗时：" + stopWatch.getTotalTimeMillis();
    }

    // 测试 xml 批量插入
    @RequestMapping("/xmlBatch")
    public String mybatisBatchXml() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ArrayList<Test> list = new ArrayList<>();
        for (int i = 1; i <= 5000; i++) {
            Test test = new Test();
            test.setId((long) i);
            test.setName("测试" + i);
            list.add(test);
        }
        testServiceImpl.getBaseMapper().insertBatch( list);
        System.out.println("5000 xmlBatch 批量保存成功");
        stopWatch.stop();
        return "xmlBatch 耗时：" + stopWatch.getTotalTimeMillis();
    }

     // 清空数据
    @RequestMapping("/clear")
    public String clear() {
        testServiceImpl.remove(null);
        return "清空数据成功";
    }

    // 查询总数
    @RequestMapping("/count")
    public String count() {
        return "数据总数：" + testServiceImpl.count();
    }
}
