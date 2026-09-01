package com.it.并发.zsh_juc_new;

public class TestSingleMain {

    private static volatile TestSingleMain testSingleMain = null;

    private TestSingleMain() {
    }

    // 双重检查锁
    // ddl
    public static TestSingleMain getInstance() {
        // B
        if (testSingleMain == null) {
            synchronized (TestSingleMain.class) {
                if (testSingleMain == null) {
                    // A   ,  开辟空间，test指向地址，初始化
                    testSingleMain = new TestSingleMain(); // 对象的生命周期
                }
            }
        }
        return testSingleMain;
    }
}
