package com.并发JUC.A_线程基础.g_线程的协作机制.线程的等待唤醒机制;

public class Synchronized_Product_Sale {
	public static void main(String[] args) {
		Resource1 resource = new Resource1();

		new Thread(() -> {
			for (int i = 0; i < 20; i++) {
				resource.add();
			}
		}, "生产者A").start();
		new Thread(() -> {
			/// 睡眠一下,制造网络延时问题,出现了超卖现象 --- > 解决方案if改while
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 20; i++) {
				resource.add();
			}
		}, "生产者B").start();
		new Thread(() -> {
			for (int i = 0; i < 20; i++) {
				resource.sale();
			}
		}, "消费者C").start();
		new Thread(() -> {
			for (int i = 0; i < 20; i++) {
				resource.sale();
			}
		}, "消费者D").start();
	}
}

class Resource1 {
	/**
	 * 产品数量
	 */
	private int Product = 0;

	/**
	 * 新增商品
	 */
	public synchronized void add() {
		while (Product >= 10) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Product++;
		System.out.println(Thread.currentThread().getName() + "新增了1件商品" + "现在剩余" + Product);
		this.notifyAll();
	}

	/**
	 * 售卖商品
	 */
	public synchronized void sale() {
		while (Product <= 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Product--;
		System.out.println(Thread.currentThread().getName() + "售卖了1件商品" + "现在剩余" + Product);
		this.notifyAll();
	}
}