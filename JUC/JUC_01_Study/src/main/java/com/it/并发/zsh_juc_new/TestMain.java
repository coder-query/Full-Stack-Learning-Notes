package com.it.并发.zsh_juc_new;

public class TestMain {
    public static void main(String[] args) {

//        // 任务
//        Runnable runnable = () -> {
//            for (int i = 0; i < 1000; i++) {
//                /// 每到50的倍数时,让出cpu执行权,进入cpu调度队列,和其他线程一起公平竞争cpu执行权
//                if (i % 50 == 0) {
//                    System.err.println("我是Runnable线程... 我现在让出cpu执行权... + i=" + i);
//                    continue;
//                }
//                System.out.println("我是Runnable线程... " + i);
//            }
//        };
//        new Thread(runnable, "Runnable线程").start();
//
//        for (int i = 0; i < 1000; i++) {
//            System.out.println("我是main线程... " + i);
//            Thread.yield();
//        }
//        Thread tA =
//                new Thread(
//                        () -> {
//                            for (int i = 0; i < 10; i++)
//                                System.out.println(Thread.currentThread().getName() + " ---> " + i);
//                        },
//                        "a线程");
//        Thread tB =
//                new Thread(
//                        () -> {
////                             b线程必须等待a线程执行完毕，才能执行
//                            try {
//                                tA.join();  // 这个线程全部执行完，会有一个结果，返回个B线程使用
//                            } catch (InterruptedException e) {
//                                throw new RuntimeException(e);
//                            }
//                            for (int i = 0; i < 10; i++)
//                                System.out.println(Thread.currentThread().getName() + " ---> " + i);
//                        },
//                        "b线程");
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println(Thread.currentThread().getName() + " ---> " + i);
//        }
//        tA.start();
//        tB.start();
        TestSingleMain instance = TestSingleMain.getInstance();

    }
}
