package com.shuai;


@SuppressWarnings("all")
public class Doubl؜eCheckedLockingSingleton {

    private static volatile Doubl؜eCheckedLockingSingleton instance;

    private Doubl؜eCheckedLockingSingleton() { }

    // 双重检查锁定结合了懒汉式的延؜迟加载和饿汉式的高性能，首次创建时加锁，后续访问则跳过同步块，从而减少锁开销。
    public static Doubl؜eCheckedLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (Doubl؜eCheckedLockingSingleton.class) {
                if (instance == null) {
                    instance = new Doubl؜eCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}

