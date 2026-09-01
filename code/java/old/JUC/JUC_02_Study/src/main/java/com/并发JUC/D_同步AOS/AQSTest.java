package com.并发JUC.D_同步AOS;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/10 星期四 1:52
 */
public class AQSTest {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();

        reentrantLock.lock();  /// 阻塞

        reentrantLock.tryLock();  /// 不阻塞

        reentrantLock.unlock();

        System.out.println((Integer.MAX_VALUE + 1));
    }
}
