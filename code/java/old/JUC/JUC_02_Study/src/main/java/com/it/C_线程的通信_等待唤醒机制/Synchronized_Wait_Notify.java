package com.it.C_线程的通信_等待唤醒机制;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/4 星期二 10:55
 */
public class Synchronized_Wait_Notify {
	public static void main(String[] args) {
		R r = new R();
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				try {
					r.incr();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}, "生产者").start();

		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				try {
					r.decr();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}, "消费者").start();

	}
}

class R {
	private int stock = 0;

	public synchronized void incr() throws InterruptedException {
		while (stock >= 5) {
			this.wait();
		}
		stock++;
		System.out.println(Thread.currentThread().getName() + "已经进货.....现在库存数量=>" + stock);
		this.notifyAll();
	}

	public synchronized void decr() throws InterruptedException {
		while (stock == 0) {
			this.wait();
		}
		stock--;
		System.out.println(Thread.currentThread().getName() + "已经消费完了商品,现在库存数量为" + stock + "请赶快进货....");
		this.notifyAll();
	}
}
