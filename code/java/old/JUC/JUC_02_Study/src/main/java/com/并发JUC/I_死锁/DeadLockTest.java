package com.并发JUC.I_死锁;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/5 星期六 9:57
 */
public class DeadLockTest {
	public static void main(String[] args) throws InterruptedException {
		String leftChopsticks = "左筷子";
		String rightChopsticks = "右筷子";

		Runnable runnable_01 = () -> {
			synchronized (leftChopsticks) {
				System.out.println("我是Runnable_01线程,我拿到了" + leftChopsticks + "正在准备拿" + rightChopsticks);
				synchronized (rightChopsticks) {
					System.out.println("我是Runnable_01线程,我现在拿到了" + rightChopsticks + ", 我现在有一双筷子,开吃...");
				}
			}
		};
		Runnable runnable_02 = () -> {
			synchronized (rightChopsticks) {
				System.out.println("我是Runnable_02线程,我拿到了" + rightChopsticks + "正在准备拿" + leftChopsticks);
				synchronized (leftChopsticks) {
					System.out.println("我是Runnable_02线程,我现在拿到了" + leftChopsticks + ", 我现在有一双筷子,开吃...");
				}
			}
		};
		new Thread(runnable_01).start();
		new Thread(runnable_02).start();

		Thread.sleep(1000);
		System.out.println("我是main线程...");
	}
}
