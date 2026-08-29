package com.并发JUC.C_Volatile关键字;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/5 星期六 17:13
 */

import java.util.concurrent.TimeUnit;

/**
 * 验证Volatile保证了数据变量的可见性
 */

/**
 * 注意
 * sleep(0)
 * - 它会让线程睡眠0秒,此时会发生线程切换,此时当cpu切换回当前线程时会重新从主内存中加载数据到工作内存
 * - sleep()方法通过让出 CPU 执行权，可能导致 JVM 在切换线程时触发内存同步操作。
 * sout打印
 * - （如 System.out.println ）内部使用 synchronized 关键字，会强制刷新线程工作内存到主内存
 */
public class VolatileTest_01 {

	public static boolean flag = true;
//	public static volatile boolean flag = true;

	public static void main(String[] args) throws InterruptedException {
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " -------come in");
			while (flag) {
				//				try {
//					Thread.sleep(0);
//				} catch (InterruptedException e) {
//					throw new RuntimeException(e);
//				}
//				System.out.println("我是子线程...");
			}
			System.out.println(Thread.currentThread().getName() + " -------flag被设置为false，程序停止");
		}, "子线程").start();


		TimeUnit.SECONDS.sleep(1);
		//更新flag值
		flag = false;
		System.out.println(Thread.currentThread().getName() + "线程将flag修改完成");
	}
}
