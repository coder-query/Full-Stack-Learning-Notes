package com.并发JUC.A_线程基础.d_线程的生命周期;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/4 星期五 23:04
 */
public class ThreadStateTest {
	public static void main(String[] args) {
		/**
		 * 线程的生命周期
		 * 1. New --> new Thread(...)
		 * 2. Runnable --> Thread.start()
		 * 3. Waiting -->  调用阻塞方法,如sleep()、join()、wait()等
		 * 4. Time_Waiting --> sleep(long time)、join()、wait(long time)
		 * 5. Blocked --> 抢到锁的进入Runnable,没抢到进入锁阻塞Blocked状态
		 * 6. Terminated --> 线程结束
		 */
		for (Thread.State value : Thread.State.values()) {
			System.out.println(value);
		}
	}
}
