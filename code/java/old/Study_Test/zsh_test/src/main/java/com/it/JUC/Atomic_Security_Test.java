package com.it.JUC;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/4 星期二 23:27
 */
public class Atomic_Security_Test {
	private static AtomicInteger atomicInteger = new AtomicInteger(0);

	public static void main(String[] args) throws InterruptedException {

		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				for (int j = 0; j < 1000; j++)
					atomicInteger.incrementAndGet(); /// 原理: lock cmpxchg 指令
			}).start();
		}
		Thread.sleep(1000);
		System.out.println(atomicInteger.get());

	}
}
