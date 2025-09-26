package org.example.b_线程常用的方法;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/9/26 0026
 */
public class 线程的强占_join_01 {
  public static void main(String[] args) throws InterruptedException {
    Thread tA =
        new Thread(
            () -> {
              for (int i = 0; i < 10; i++)
                System.out.println(Thread.currentThread().getName() + " ---> " + i);
            },
            "a线程");
    Thread tB =
        new Thread(
            () -> {
              // b线程必须等待a线程执行完毕，才能执行
              try {
                tA.join();
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
