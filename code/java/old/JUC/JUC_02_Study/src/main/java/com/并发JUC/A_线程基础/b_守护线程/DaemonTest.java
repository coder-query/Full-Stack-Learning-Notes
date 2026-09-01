package com.并发JUC.A_线程基础.b_守护线程;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/4 星期五 21:02
 */
public class DaemonTest {
	public static void main(String[] args) throws InterruptedException {
		Runnable runnable = () -> {
			for (int i = 0; i < 1000; i++) {
				System.out.println("我是Runnable线程 ... " + i);
			}
		};
		Thread thread = new Thread(runnable);
		thread.setDaemon(true);
		thread.start();

		Thread.sleep(5);
		System.out.println("我是main线程 ,我马上结束了... ");
	}
}
