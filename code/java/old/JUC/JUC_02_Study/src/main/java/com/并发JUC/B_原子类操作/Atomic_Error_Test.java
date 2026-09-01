package com.并发JUC.B_原子类操作;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/4 星期二 23:57
 */
public class Atomic_Error_Test {

	//	private synchronized static void incr() {
//		num++;
//	}
	private static int num = 0;

	private static void incr() {
		num++;  // num = num + 1  --->  1. 读取num到线程工作内存 2. 自增+1  3. 写入主内存
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			/// new 10个线程
			new Thread(() -> {
				for (int j = 0; j < 5000; j++)
					incr();
			}).start();
		}
		Thread.sleep(3000);
		System.out.println(num);
	}
}
