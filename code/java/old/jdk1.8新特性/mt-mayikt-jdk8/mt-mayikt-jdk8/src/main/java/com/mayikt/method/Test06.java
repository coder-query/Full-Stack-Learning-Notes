package com.mayikt.method;

import com.mayikt.entity.OrderEntity;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @ClassName Test06
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test06 {
    private static OrderEntity order = null;

    public static void main(String[] args) {
        OrderEntity order = Test06.getOrder();
        System.out.println(order);

    }

    public static OrderEntity getOrder() {
//        // 优化前
//        if (order == null) {
//            return createOrder();
//        }
//        return order;
        // 优化后
//        return Optional.ofNullable(order).orElseGet(new Supplier<OrderEntity>() {
//            @Override
//            public OrderEntity get() {
//                return createOrder();
//            }
//        });
        return Optional.ofNullable(order).orElseGet(() -> createOrder());
    }

    private static OrderEntity createOrder() {
        return new OrderEntity("123456", "mayikt");
    }
}
