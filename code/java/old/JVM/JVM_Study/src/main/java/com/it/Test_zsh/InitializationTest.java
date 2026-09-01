package com.it.Test_zsh;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/27 星期四 11:20
 */
public class InitializationTest {
    public static int val = 1;

    static {
        val = 2;
    }

    public static void main(String[] args) {
        System.out.println(val);
    }
}
