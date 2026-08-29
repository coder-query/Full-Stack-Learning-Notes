package org.example.yuanzi;


import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *
 * @author shuaihong-coding
 * @date 2026-03-09 23:10
 */
public class AtomicTest {
    static Object obj = new Object();

    static volatile int num = 0;

    public static void increment() {
       synchronized (obj){
           num++;
       }
    }

    public static void main(String[] args) throws NoSuchFieldException {
        AtomicInteger atomicInt = new AtomicInteger();
        atomicInt.getAndAdd(1);
        AtomicStampedReference atomicStampedRef = new AtomicStampedReference(atomicInt,1);
        atomicInt.getAndIncrement();
        Unsafe.getUnsafe()
                .compareAndSwapObject(
                        obj,
                        Unsafe.getUnsafe().objectFieldOffset(AtomicInteger.class.getDeclaredField("value")),
                        null,
                        new Object()
                );
    }
}
