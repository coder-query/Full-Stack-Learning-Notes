package com.并发JUC.C_Volatile关键字;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/4 星期二 23:57
 */

import java.util.concurrent.TimeUnit;

/**
 * 验证Volatile没有保证数据变量的原子性,存在线程并发问题
 */
public class VolatileTest_02 {

	private static volatile int num = 0;

	private static void incr() {
		num++;
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			/// new 10个线程
			new Thread(() -> {
				for (int j = 0; j < 5000; j++)
					incr();
			}).start();
		}
		TimeUnit.SECONDS.sleep(1);
		System.out.println(num);
	}
}
