package com.并发JUC.D_同步AOS.自定义同步器;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/10 星期四 22:30
 */

/**
 * 判断当前线程是不是持有该锁的对象
 * - 是 :
 * 则放行,让其进入监视区
 * - 否 :
 * 判断是不是第一次进入锁 是则设置当前线程为该锁持有者对象,不是则返回false
 */
public class MutexLock {

	static final class Sync extends AbstractQueuedSynchronizer {
		@Override
		protected boolean tryAcquire(int arg) {
			if (getState() == 0) {
				if (compareAndSetState(0, 1)) {
					setExclusiveOwnerThread(Thread.currentThread());
					return true;
				}
			} else if (Thread.currentThread() == getExclusiveOwnerThread()) {
				return true;
			}
			return false;
		}

		@Override
		protected boolean tryRelease(int arg) {
			setExclusiveOwnerThread(null);
			setState(0);
			return true;
		}
	}

	private final Sync sync = new Sync();

	public void lock() {
		sync.acquire(0);
	}

	public void unlock() {
		sync.release(0);
	}
}
