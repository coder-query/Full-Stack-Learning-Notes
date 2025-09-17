package com.并发JUC.A_线程基础.c_线程的常用方法;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/4 星期五 21:34
 */
public class YieldTest {
	public static void main(String[] args) {
		Runnable runnable = () -> {
			for (int i = 0; i < 1000; i++) {
				System.out.println("我是Runnable线程... " + i);
				/// 每到50的倍数时,让出cpu执行权,进入调度队列,和其他线程一起公平竞争cpu执行权
				if (i % 50 == 0) {
					System.err.println("我是Runnable线程... 我现在让出cpu执行权...");
					Thread.yield();
				}
			}
		};
		new Thread(runnable).start();

		for (int i = 0; i < 1000; i++) {
			System.out.println("我是main线程... " + i);
		}

	}
}
