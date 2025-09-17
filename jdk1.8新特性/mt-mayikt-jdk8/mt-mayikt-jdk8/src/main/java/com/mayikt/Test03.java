package com.mayikt;

import com.mayikt.service.AcanthopanaxInterface;

/**
 * @ClassName Test03
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test03 {
    public static void main(String[] args) {
        //1.使用匿名内部类调用
        new AcanthopanaxInterface() {
            @Override
            public void get() {
                System.out.println("get");
            }
        }.get();
        AcanthopanaxInterface acanthopanaxInterface=   ()->{
            System.out.println("使用lamdba表达式调用方法");
        };
        acanthopanaxInterface.get();

    }
}
