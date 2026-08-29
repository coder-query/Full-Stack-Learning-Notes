package com.并发JUC.A_线程基础.a_线程的启动和终止.线程的创建方式;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/4 星期五 14:20
 */
public class CallableTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Test_03 callableThread = new Test_03();

        FutureTask<String> futureTask = new FutureTask<>(callableThread);

        new Thread(futureTask).start();

        System.out.println(futureTask.get()); // **阻塞**

        System.out.println("我是main线程...");
    }
}

class Test_03 implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(5000);  // 模拟业务执行
        return "我是Callable接口实现的线程...";
    }
}
