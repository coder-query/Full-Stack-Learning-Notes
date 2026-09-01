package com.并发JUC.C_并发工具类;

import java.util.concurrent.CountDownLatch;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/18 星期二 16:05
 */
public class CountDownLatch_Thread {
	/**
	 * 主要是倒计时,用一个await()方法阻塞后续要执行的线程
	 *
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(6);

		for (int i = 0; i < 6; i++) {
			new Thread(() -> {
				/// 模拟学生在收拾书包
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				/// 学生书包收拾完毕,走出教室...
				System.out.println(Thread.currentThread().getName() + "学生,离开了教室");
				countDownLatch.countDown();
				System.out.println(Thread.currentThread().getName() + "我是搅屎棍...");
			}, String.valueOf(i + 1)).start();
		}

		countDownLatch.await();
		System.out.println("老师锁门..");
	}
}
