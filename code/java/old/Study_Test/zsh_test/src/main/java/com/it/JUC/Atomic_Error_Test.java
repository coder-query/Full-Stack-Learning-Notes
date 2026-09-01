package com.it.JUC;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/4 星期二 23:57
 */
public class Atomic_Error_Test {

//	private synchronized static void incr() {
//		num++; /// num = num + 1;  第一步: 读取  第二步: 自增修改,并写回主内存
//	}

	private static int num = 0;

	private static void incr() {
		num++; /// num = num + 1;  第一步: 读取  第二步: 自增修改,并写回主内存
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			/// new 10个线程
			new Thread(() -> {
				for (int j = 0; j < 1000; j++)
					incr();
			}).start();
		}
		Thread.sleep(1000);
		System.out.println(num);
	}
}
