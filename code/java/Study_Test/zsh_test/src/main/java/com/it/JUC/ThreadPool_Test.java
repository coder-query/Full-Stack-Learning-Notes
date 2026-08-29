package com.it.JUC;

import java.util.concurrent.*;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/5 星期三 20:45
 */
public class ThreadPool_Test {
	public static void main(String[] args) {

		/**
		 * 下面的四种方式不推荐,会导致OOM
		 */
		/// 阻塞队列长度为Integer.MAX_VALUE,可能会堆积大量的请求，从而导致 OOM。
		ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);

		/// 线程池的最大线程数为Integer.MAX_VALUE,可能会创建大量的线程，从而导致 OOM。
		ExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

		/// 推荐使用自定义线程池
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
				2, /// 核心线程数
				6, /// 最大线程数
				30, /// 临时线程空闲时的最大存活时间 (值)
				TimeUnit.SECONDS, /// 临时线程空闲时的最大存活时间 (单位)
				new ArrayBlockingQueue<>(2), /// 任务阻塞队列 // 0 1
				new ThreadPoolExecutor.AbortPolicy()); /// 任务的拒绝策略

		poolExecutor.execute(() -> {
			System.out.println("hello 靓仔");
		});

//		poolExecutor.setRejectedExecutionHandler(new My_Reject_Handle()); /// 自定义任务拒绝策略

		for (int i = 0; i < 100; i++)
			poolExecutor.execute(() -> {
				System.out.println("hello 靓仔");
			});
	}

}
