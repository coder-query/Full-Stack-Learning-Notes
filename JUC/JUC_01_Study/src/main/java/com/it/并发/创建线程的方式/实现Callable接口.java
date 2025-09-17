package com.it.并发.创建线程的方式;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class 实现Callable接口 {
    /**
     * 创建 一个类MyCallable实现Callable接口
     * 重写call()
     * 创建MyCallable的对象  (表示多线程要执行的任务)
     * 创建FutureTask对象  (作为管理多线程运行的结果)
     * 创建Thread对象  (启动线程)
     */
    public static void main(String[] args) {

        MyCallable myCallable = new MyCallable(); //(表示多线程要执行的任务)

        FutureTask futureTask = new FutureTask<Integer>(myCallable);  //(作为管理多线程运行的结果)

        new Thread(futureTask).start(); //(启动线程)

        //获取线程运行结果
        try {
            Thread.sleep(10000);
            Integer sum = (Integer) futureTask.get();
            System.out.println("sum = " + sum);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
       int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}
