package com.it.SE;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/4 星期二 23:18
 */
public class Java_Object_Lock {
	public static void main(String[] args) throws InterruptedException {
		User user1 = new User();
		System.out.println("无状态:001  " + ClassLayout.parseInstance(user1).toPrintable());

		Thread.sleep(5000); /// 睡眠5秒,等待偏向锁启动
		User user2 = new User();
		System.out.println("偏向锁标记启动:101  " + ClassLayout.parseInstance(user2).toPrintable());

		for (int i = 0; i < 2; i++) {
			synchronized (user2) {
				System.out.println("偏向锁(101) (带线程id)  " + ClassLayout.parseInstance(user2).toPrintable());
			}
			System.out.println("偏向锁释放(101) (带线程id)  " + ClassLayout.parseInstance(user2).toPrintable());
		}
		synchronized (user1) {
			System.out.println(ClassLayout.parseInstance(user1).toPrintable());
		}

	}
}

class User {
	private double money;
	private int age;
}
