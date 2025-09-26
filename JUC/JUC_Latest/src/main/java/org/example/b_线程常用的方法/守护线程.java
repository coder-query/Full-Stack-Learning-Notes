package org.example.b_线程常用的方法;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/9/27 0027
 */
public class 守护线程 {
  public static void main(String[] args) {
    Thread t1 =
        new Thread(
            () -> {
              for (int i = 0; i < 1000; i++)
                System.out.println(Thread.currentThread().getName() + " ---> " + i);
            },
            "线程1");
    t1.setDaemon(true);
    t1.start();
    System.out.println("主线程结束");
  }
}
