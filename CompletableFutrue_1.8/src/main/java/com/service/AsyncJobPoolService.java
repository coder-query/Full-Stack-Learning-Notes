package com.service;


import com.job.ImportFileJob1;
import com.job.ImportFileJob2;
import com.job.fail.FailedJob;
import com.util.ThreadLocalUtil;
import com.util.TransmittableThreadUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadPoolExecutor;


@Service
public class AsyncJobPoolService {

//    @Resource
//    private ThreadPoolTaskExecutor asyncExecutorPool1;
//
//    @Resource
//    private ThreadPoolTaskExecutor asyncExecutorPool2;


    @Async(value = "asyncExecutorPool1")
//    @Async(value = "asyncExecutorPool2")
//    @Async
    public String runJob(){
        String s = ThreadLocalUtil.get();
        System.out.println( Thread.currentThread().getName() +"  我在Async代码块里，现在从ThreadLocalUtil获取数据 ++++--->  " + s);
        s = TransmittableThreadUtil.get();
        System.out.println( Thread.currentThread().getName()+ "  我在Async代码块里，现在从TransmittableThreadUtil获取数据 ++++--->  " + s);
        System.out.println("[" + Thread.currentThread().getName() + "] AsyncJobPoolService.runJob() 开始执行");

        List<Runnable> jobList = createJobList();

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("[" + Thread.currentThread().getName() + "] AsyncJobPoolService.runJob() 任务完成");
        return "success";
    }
    private List<Runnable> createJobList() {
        ImportFileJob2 importFileJob0 = new ImportFileJob2();
        ImportFileJob1 importFileJob1 = new ImportFileJob1();
        ImportFileJob1 importFileJob2 = new ImportFileJob1();
        ImportFileJob1 importFileJob3 = new ImportFileJob1();
        ImportFileJob1 importFileJob4 = new ImportFileJob1();
        ImportFileJob1 importFileJob5 = new ImportFileJob1();
        ImportFileJob1 importFileJob6 = new ImportFileJob1();
        ImportFileJob1 importFileJob7 = new ImportFileJob1();
        ImportFileJob1 importFileJob8 = new ImportFileJob1();
        ImportFileJob1 importFileJob9 = new ImportFileJob1();
        ImportFileJob1 importFileJob10 = new ImportFileJob1();
        ImportFileJob1 importFileJob11 = new ImportFileJob1();
        ImportFileJob1 importFileJob12 = new ImportFileJob1();
        ImportFileJob1 importFileJob13 = new ImportFileJob1();
        ImportFileJob1 importFileJob14 = new ImportFileJob1();
        ImportFileJob1 importFileJob15 = new ImportFileJob1();

        return new CopyOnWriteArrayList<Runnable>() {{
            add(importFileJob0);
            add(importFileJob1);
            add(importFileJob2);
            add(importFileJob3);
            add(importFileJob4);
            add(importFileJob5);
            add(importFileJob6);
            add(importFileJob7);
            add(importFileJob8);
            add(importFileJob9);
            add(importFileJob10);
            add(importFileJob11);
            add(importFileJob12);
            add(importFileJob13);
            add(importFileJob14);
            add(importFileJob15);
            // ... 添加其他任务
        }};
    }
}
