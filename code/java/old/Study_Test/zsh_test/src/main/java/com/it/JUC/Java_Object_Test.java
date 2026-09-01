package com.it.JUC;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/5 星期三 16:59
 */
public class Java_Object_Test {
	public static void main(String[] args) throws InterruptedException {
		User user = new User();
		System.out.println("无状态: 001(普通对象)  " + ClassLayout.parseInstance(user).toPrintable());


		/**
		 * jvm默认延时4s自动开启偏向锁,可通过 -XX BiasedLockingStartupDelay=0 来取消延时
		 * 如果要关闭偏向锁 则可通过 -XX:-UseBiasedLocking = false 来关闭
		 */

//		Thread.sleep(5000);
//		User user = new User();
//		System.out.println("偏向锁: 101(匿名对象)  " + ClassLayout.parseInstance(user).toPrintable());
//
//		/// main线程的id被记录在了Markword中
//		synchronized (user) {
//			System.out.println("偏向锁: 101(线程id)" + ClassLayout.parseInstance(user).toPrintable());
//		}

	}
}

class User {
	private byte gender;
	private double money;
	private int age;
}

