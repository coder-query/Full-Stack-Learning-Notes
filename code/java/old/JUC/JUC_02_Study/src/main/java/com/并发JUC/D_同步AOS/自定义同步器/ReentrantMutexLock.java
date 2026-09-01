package com.并发JUC.D_同步AOS.自定义同步器;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/10 星期四 22:49
 */
public class ReentrantMutexLock {
	static final class Sync extends AbstractQueuedSynchronizer {
		@Override
		protected boolean tryAcquire(int arg) {
			return compareAndSetState(0, 1);
		}

		@Override
		protected boolean tryRelease(int arg) {
			setState(0);
			return true;
		}
	}

	private final MutexLock.Sync sync = new MutexLock.Sync();

	public void lock() {
		sync.acquire(0);
	}

	public void unlock() {
		sync.release(0);
	}
}
