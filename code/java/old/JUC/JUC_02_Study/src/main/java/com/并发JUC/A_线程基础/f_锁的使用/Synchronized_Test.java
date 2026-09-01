package com.并发JUC.A_线程基础.f_锁的使用;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/7 星期一 19:04
 */
public class Synchronized_Test {

	private synchronized static void method_01() {
		String threadName = Thread.currentThread().getName();
		System.out.println("当前线程名称 : " + threadName);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private synchronized void method_02() {
		String threadName = Thread.currentThread().getName();
		System.out.println("当前线程名称 : " + threadName);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private void method_03() {
		synchronized (this) {
			String threadName = Thread.currentThread().getName();
			System.out.println("当前线程名称 : " + threadName);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void main(String[] args) {
		Synchronized_Test syncTest_01 = new Synchronized_Test();
		Synchronized_Test syncTest_02 = new Synchronized_Test();

		/**
		 * 锁的是 Synchronized_Test.class对象
		 */
		new Thread(() -> {
			method_01();
		}, "A").start();

		new Thread(() -> {
			method_01();
		}, "B").start();


		/**
		 * 锁的是syncTest_01对象
		 */
//		new Thread(() -> {
//			syncTest_01.method_02();
//		}, "A").start();
//
//		new Thread(() -> {
//			syncTest_01.method_02();
//		}, "B").start();


//		new Thread(() -> {
//			syncTest_01.method_02();
//		}, "A").start();
//
//		new Thread(() -> {
//			syncTest_02.method_02();
//		}, "B").start();


	}
}
