package com.顺序打印ABC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ReentrantLock_ABC {

	private static int num = 0;
	private static final ReentrantLock lock = new ReentrantLock();
	private static final Condition condition = lock.newCondition();

	private void printABC(int targetNum) {
		for (int i = 0; i < 100; i++) {
			try {
				lock.lock();
				while (num % 3 != targetNum) {
					try {
						condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				num++;
				if (num <= 100)
					System.out.println(Thread.currentThread().getName() + " 打印 " + num);
				condition.signal();
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		ReentrantLock_ABC wait_notify_acb = new ReentrantLock_ABC();
		for (int i = 0; i < 3; i++) {
			int temp = i;
			new Thread(() -> {
				wait_notify_acb.printABC(temp);
			}, "线程" + String.valueOf(i + 1)).start();
		}
	}

}