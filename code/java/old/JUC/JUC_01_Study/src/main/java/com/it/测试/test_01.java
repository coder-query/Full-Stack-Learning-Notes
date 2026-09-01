package com.it.测试;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/18 星期二 19:52
 */
public class test_01 {
    /**
     * 可重入锁测试
     *
     * @param args
     */
    public static void main(String[] args) {
        new Fun().aa();
    }
}

class Fun {
    public synchronized void aa() {
        System.out.println("aa");
        bb();
    }

    public synchronized void bb() {
        System.out.println("bb");
    }
}
