package com.并发JUC.A_线程基础.a_线程的启动和终止.线程的终止;

/**
 * @author 帅宏-coding @Money java_offer_13k
 * @date 2025/4/4 星期五 20:31
 */

// 匿名内部类
public class InterruptTest {
  public static void main(String[] args) throws InterruptedException {

    /**
     * Interrupt() : 设置线程的状态flag改为true : 线程终止 接口可以 new 匿名内部类 1.8 新特性 Lambda (参数列表)->{方法体}; /// 简化
     * 函数接口的方法 的重写
     */
    Runnable runnable =
        () -> {
          Thread currentThread = Thread.currentThread();
          /// 接受信号
          while (!currentThread.isInterrupted()) {
            System.out.println("Runnable 线程...");
          }

          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          // false  ---> true  ---> false
          //          while (!Thread.interrupted()) {
          //            System.out.println("Runnable 线程...");
          //          }

          while (!currentThread.isInterrupted()) {
            System.out.println("我是后续处理...");
          }
        };

    Thread thread = new Thread(runnable);
    thread.start();

    Thread.sleep(1000);
    thread.interrupt(); // / 发送信号 main线程执行的
    System.out.println("我是main,我已经发送中断信号...");
  }
}
