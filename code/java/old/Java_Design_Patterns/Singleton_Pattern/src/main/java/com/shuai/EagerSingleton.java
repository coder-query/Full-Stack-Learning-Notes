package com.shuai;

@SuppressWarnings("all")
// 饿汉式是在类加载阶段就完成实例化，保؜证从第一次访问该类到程序结束，全局只有这一个实例。它依赖 JVM 的类加载机制来确保线程安全。
public class EagerSingleton {

  private EagerSingleton() {}

  private static final EagerSingleton instance = new EagerSingleton();

  public static EagerSingleton getInstance() {
    return instance;
  }
}
