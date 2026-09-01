package com.util;

import com.alibaba.ttl.TransmittableThreadLocal;

public class TransmittableThreadUtil {
    public static final TransmittableThreadLocal< String> threadLocal = new TransmittableThreadLocal<>();

    public static void set(String value) {
        threadLocal.set(value);
    }
    public static String get() {
        return threadLocal.get();
    }
    public static void remove() {
        threadLocal.remove();
    }
}
