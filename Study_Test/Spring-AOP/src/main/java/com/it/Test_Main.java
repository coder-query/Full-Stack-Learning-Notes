package com.it;

import com.Test_Interface;

public class Test_Main {
    public static void main(String[] args) {
        Test_Interface testInterface = () -> {
            System.out.println("我是Lambda...");
        };  /// 生成代理对象的方法

        System.out.println("sadtyu");
        System.out.println("asdfgyui");


        // 延迟执行
        testInterface.fun();

    }
}
