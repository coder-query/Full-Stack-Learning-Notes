package com.it;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author shuaihong-coding
 * @date 2026-02-15 21:38
 */
public class 持续栈溢出 {
    static AtomicInteger atomicInteger = new AtomicInteger();

    public static void funPrint() {
        System.out.println(atomicInteger.getAndIncrement());
        funPrint();
    }

    public static void main(String[] args) {
        while (true) {
            funPrint();
        }


    }

}
