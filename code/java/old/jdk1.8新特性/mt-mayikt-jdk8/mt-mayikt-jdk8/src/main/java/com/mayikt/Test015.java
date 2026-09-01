package com.mayikt;

import com.mayikt.entity.OrderEntity;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @ClassName Test015
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test015 {
    private static OrderEntity orderEntity;

    public static void main(String[] args) {
        OrderEntity order = getOrder();
        System.out.println(order);
    }

    public static OrderEntity getOrder() {
//        if (orderEntity == null) {
//            return createOrder();
//        }
        orderEntity = Optional.ofNullable(orderEntity).orElseGet(() -> createOrder());
        return orderEntity;
    }

    public static OrderEntity createOrder() {
        return new OrderEntity("1234", "mayikt");
    }

}
