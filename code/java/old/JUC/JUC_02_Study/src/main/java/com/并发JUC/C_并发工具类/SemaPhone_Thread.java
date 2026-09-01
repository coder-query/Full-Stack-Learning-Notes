package com.并发JUC.C_并发工具类;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/18 星期二 19:26
 */
public class SemaPhone_Thread {
	public static void main(String[] args) {
		// 初始化信号量，3个车位
		Semaphore semaphore = new Semaphore(3);

		// 5个线程，模拟5辆车
		for (int i = 0; i < 6; i++) {
			new Thread(() -> {
				try {
					// 抢占一个停车位
					semaphore.acquire();
					System.out.println(Thread.currentThread().getName() + " 抢到了一个停车位！！");
					// 停一会儿车
					TimeUnit.SECONDS.sleep(new Random().nextInt(10));
					System.out.println(Thread.currentThread().getName() + " 离开停车位！！");
					// 开走，释放一个停车位
					semaphore.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}
	}
}
