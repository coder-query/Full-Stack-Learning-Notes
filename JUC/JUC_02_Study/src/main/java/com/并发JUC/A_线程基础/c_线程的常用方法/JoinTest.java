package com.并发JUC.A_线程基础.c_线程的常用方法;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/4 星期五 22:01
 */
public class JoinTest {
	public static void main(String[] args) throws InterruptedException {
		Runnable runnable = () -> {
			System.err.println("子业务开始执行...");
			for (int i = 0; i < 1000; i++) {
				System.err.println("子业务执行中..." + i);
			}
			System.err.println("子业务执行完毕...");
		};
		Thread thread = new Thread(runnable);

		for (int i = 0; i < 1000; i++) {
			System.out.println("主业务执行中..." + i);
			if (i == 10) {
				thread.start();
				thread.join();
				System.out.println("子业务执行完毕...主业务即将继续执行...");
			}
		}
	}
}
