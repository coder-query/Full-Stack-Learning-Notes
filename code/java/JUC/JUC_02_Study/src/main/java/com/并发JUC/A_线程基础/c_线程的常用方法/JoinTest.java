package com.并发JUC.A_线程基础.c_线程的常用方法;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/4 星期五 22:01
 */
public class JoinTest {
	public static void main(String[] args) throws InterruptedException {
//		Runnable runnable = () -> {
//			System.err.println("子业务开始执行...");
//			for (int i = 0; i < 1000; i++) {
//				System.err.println("子业务执行中..." + i);
//			}
//			System.err.println("子业务执行完毕...");
//		};
//		Thread thread = new Thread(runnable);
//
//		for (int i = 0; i < 1000; i++) {
//			System.out.println("主业务执行中..." + i);
//			if (i == 10) {
//				thread.start();
//				thread.join();
//				System.out.println("子业务执行完毕...主业务即将继续执行...");
//			}
//		}

//		Runnable runTask001 = ()->{
//			for (int i = 0; i < 100; i++){
//				System.out.println("我是Runnable线程---001... " + i);
//			}
//		};
//		Runnable runTask002 = ()->{
//			for (int i = 0; i < 100; i++){
//				System.out.println("我是Runnable线程---002... " + i);
//			}
//		};
//
//		Thread thread001 = new Thread(runTask001);
//		Thread thread002 = new Thread(runTask002);
//
//		thread002.setPriority(Thread.MAX_PRIORITY);

		// 先启动 runTask001 ， 再启动 runTask002
		// 正常效果是先启动，先执行完
		// 如果设置了优先级，可以稍微的控制一下线程的执行时机，但是这个主要是取决于cpu的实际调度
//		thread001.start();
//		thread002.start();

	}
}
