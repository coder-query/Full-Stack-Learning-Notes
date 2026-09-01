package com.mayikt;

import com.mayikt.service.YouShenInterface;

/**
 * @ClassName Test04
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test04 {
    public static void main(String[] args) {
        //1.使用匿名内部类调用
//        YouShenInterface youShenInterface = new YouShenInterface() {
//            @Override
//            public String get(int i, int j) {
//                return i + "--" + j;
//            }
//        };
//        System.out.println(youShenInterface.get(1, 2));
        //2.使用lamdba 调用有参数函数方法
        YouShenInterface youShenInterface2 = (i, j) -> {
            return i + "--" + j;
        };
        System.out.println(youShenInterface2.get(1, 1));
    }
}
