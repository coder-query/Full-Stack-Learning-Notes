package com.it.饿汉式;


/**
 * 饿汉式
 * 饿汉就是类一旦加载，就把单例初始化完成，保证getInstance的时候，单例是已经存在的了,确保唯一性
 */

public class Singleton {
    //私有化构造器
    private Singleton() {}
    //创建一个静态对象
    private static final Singleton single = new Singleton();
    //静态工厂方法
    public static Singleton getInstance() {
        return single;
    }

}
