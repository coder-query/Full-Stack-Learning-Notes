package com.mayikt;

import com.mayikt.entity.OrderEntity;

import java.util.Optional;

/**
 * @ClassName Test018
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test018 {
    public static void main(String[] args) {
        System.out.println(Test018.getOrderName());
    }

    public static String getOrderName() {
        // 优化前写法：
        OrderEntity order = new OrderEntity("123456", "MAyikt");
//        if (order != null) {
//            String orderName = order.getOrderName();
//            if (orderName != null) {
//                return orderName.toLowerCase();
//            }
//        }
        // 对我们对象中方法 实现处理
        return Optional.ofNullable(order)
                .map((orderEntity -> orderEntity.getOrderName())).map((orderName)
                        -> orderName.toLowerCase()).orElse("df");

    }
}
