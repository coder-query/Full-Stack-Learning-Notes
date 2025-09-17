package com.it.懒汉式;

/**
 * 懒汉式
 *懒汉比较懒，只有当调用getInstance的时候，才回去初始化这个单例
 */

public class Singleton {
    //私有化构造方法
    private Singleton() {}
    //创建一个静态对象
    private static Singleton single=null;
    //静态工厂方法
    public static Singleton getInstance() {
        if (single == null) {
            single = new Singleton();
        }
        return single;
    }

    //加锁(双重检查锁定)
    public static Singleton getInstance_security() {
        if (single == null) {
            synchronized (Singleton.class) {
                if (single == null) {
                    single= new Singleton();
                }
            }
        }
        return single;
    }
}
