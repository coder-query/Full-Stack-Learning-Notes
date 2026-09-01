package com.并发JUC.C_并发工具类;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/18 星期二 16:30
 */
public class CyclicBarrier_Thread {
	public static void main(String[] args) {

		CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
			System.out.println("恭喜3个玩家都过关了,开启下一关");
			System.out.println("-----------------------------------------");
		});

		for (int i = 0; i < 3; i++) {
			new Thread(() -> {
				try {
					System.out.println(Thread.currentThread().getName() + " 开始第一关");
					TimeUnit.SECONDS.sleep(new Random().nextInt(4));
					System.out.println(Thread.currentThread().getName() + " 开始打boss");
					cyclicBarrier.await();

					System.out.println(Thread.currentThread().getName() + " 开始第二关");
					TimeUnit.SECONDS.sleep(new Random().nextInt(4));
					System.out.println(Thread.currentThread().getName() + " 开始打boss");
					cyclicBarrier.await();

					System.out.println(Thread.currentThread().getName() + " 开始第三关");
					TimeUnit.SECONDS.sleep(new Random().nextInt(4));
					System.out.println(Thread.currentThread().getName() + " 开始打boss");
					cyclicBarrier.await();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
	}
}
