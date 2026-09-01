package com.并发JUC.A_线程基础.b_守护线程;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/4 星期五 21:13
 */
public class DaemonNoticeFinally {
    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> {
            try {
                for (int i = 0; i < 1000; i++) {
                    System.out.println("我是Runnable线程 ... " + i);
                }
            } finally {
                System.out.println("我是守护进程中的finally()方法...");
            }
        };

        Thread thread = new Thread(runnable);
        thread.setDaemon(true); // 设置成了守护线程
        thread.start();

        Thread.sleep(5);
        System.out.println("我是main线程 ,我马上结束了... ");
    }
}
