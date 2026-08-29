package com.mayikt.method;

import com.mayikt.entity.OrderEntity;

import java.util.Optional;

/**
 * @ClassName Test07
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test07 {
    public static void main(String[] args) {
        String orderName = Test07.getOrderName();
        System.out.println(orderName);
    }

    public static String getOrderName() {
        // 优化前写法：
        OrderEntity order = new OrderEntity("123456", "MAyikt");
        if (order != null) {
            String orderName = order.getOrderName();
            if (orderName != null) {
                return orderName.toLowerCase();
            }
        }
//        return null;
        //优化后写法:
        return Optional.ofNullable(order).map(orderEntity -> {
            return orderEntity.getOrderName();
        }).map(name -> {
            return name.toLowerCase();
        }).orElse(null);
    }
}
