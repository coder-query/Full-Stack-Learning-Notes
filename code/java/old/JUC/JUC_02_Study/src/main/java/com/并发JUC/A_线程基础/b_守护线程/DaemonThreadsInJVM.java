package com.并发JUC.A_线程基础.b_守护线程;

import java.util.Set;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/4 星期五 21:09
 */
public class DaemonThreadsInJVM {
	public static void main(String[] args) {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		for (Thread thread : threadSet) {
			System.out.println(thread.getName()
					+ " ---> " + thread.getPriority()
					+ " ---> " + thread.isDaemon());
		}
	}
}
