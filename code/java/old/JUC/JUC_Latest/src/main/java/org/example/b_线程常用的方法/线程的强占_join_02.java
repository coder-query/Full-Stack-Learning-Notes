package org.example.b_线程常用的方法;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/9/26 0026
 */
public class 线程的强占_join_02 {
  public static void main(String[] args) throws InterruptedException {
    Thread tA =
        new Thread(
            () -> {
              System.out.println(Thread.currentThread().getName() + " ---> 已经执行完了。。。");
            },
            "a线程");
    Thread tB =
        new Thread(
            () -> {
              // 让b线程等待a线程执行（10s）完毕，才能执行
                // 但是，如果a线程在还没到10s的时候，就执行完了，这时候cpu会调度b线程执行。不会真的让b线程等待10s
              try {
                tA.join(1000);
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
              for (int i = 0; i < 10; i++)
                System.out.println(Thread.currentThread().getName() + " ---> " + i);
            },
            "b线程");

    for (int i = 0; i < 10; i++)
      System.out.println(Thread.currentThread().getName() + " ---> " + i);
    tA.start();
    tB.start();
  }
}
