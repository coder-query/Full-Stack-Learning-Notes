package com.顺序打印ABC;

class Wait_Notify_ABC {

	private static int num = 0;
	private static final Object LOCK = new Object();

	private void printABC(int targetNum) {
		for (int i = 0; i < 100; i++) {
			synchronized (LOCK) {
				while (num % 3 != targetNum) {
					try {
						LOCK.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				num++;
				if (num <= 100)
					System.out.println(Thread.currentThread().getName() + " 打印 " + num);
				LOCK.notifyAll();
			}
		}
	}

	public static void main(String[] args) {
		Wait_Notify_ABC wait_notify_acb = new Wait_Notify_ABC();
		for (int i = 0; i < 3; i++) {
			int temp = i;
			new Thread(() -> {
				wait_notify_acb.printABC(temp);
			}, "线程" + String.valueOf(i + 1)).start();
		}
	}
}