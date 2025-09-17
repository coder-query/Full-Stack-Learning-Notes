package com.mayikt;

import com.mayikt.service.AcanthopanaxInterface;
import com.mayikt.service.YouShenInterface;

/**
 * @ClassName Test05
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test05 {
    public static void main(String[] args) {
        AcanthopanaxInterface acanthopanaxInterface = () -> System.out.println("我是方法");
//        acanthopanaxInterface.get();
        // 精简代码         // 使用Lambda 方法体中只有一条语句的情况下，在这时候我们不需要写{}  也可以不需要写return
//        ((AcanthopanaxInterface) () -> System.out.println("我是方法")).get();
//        YouShenInterface youShenInterface = (i, j) -> {
//            return i + "-" + j;
//        };
//        YouShenInterface youShenInterface2 = (i, j) -> i + "-" + j;
        String s = ((YouShenInterface) (i, j) -> i + "-" + j).get(1, 2);
        System.out.println(s);

    }
}
