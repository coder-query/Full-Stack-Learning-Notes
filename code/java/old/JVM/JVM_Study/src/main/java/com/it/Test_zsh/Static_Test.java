package com.it.Test_zsh;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/2/25 星期二 0:30
 */
public class Static_Test {
    private static int a = 3;
    public static int b = 3;

    public static void main(String[] args) {
        Test_Inner_Class.print();
    }

    static class Test_Inner_Class {
        public static void print() {
            System.out.println(a);
        }
    }
}
