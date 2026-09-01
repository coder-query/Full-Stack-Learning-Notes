package com.mayikt;

import com.mayikt.entity.OrderEntity;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @ClassName Test21
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test21 {
    private static OrderEntity orderEntity;

    public static void main(String[] args) {
        System.out.println(Test21.getOrder());
        System.out.println(Test21.orderEntity);
    }

    public static OrderEntity getOrder() {
        // 优化前
//        if (orderEntity == null) {
//            orderEntity = createOrder();
//        }
        /**
         * orElseGet()---写函数的接口的形式 赋默认值
         * orElse()----直接传递默认值
         */
//        return Optional.ofNullable(orderEntity).orElse(createOrder());
        return Optional.ofNullable(orderEntity).orElseGet(Test21::orElseGet);
//        return orderEntity;
    }

    private static OrderEntity createOrder() {
        return new OrderEntity("123456", "mayikt");
    }

    public static OrderEntity orElseGet() {
        orderEntity = createOrder();
        return orderEntity;
    }
}
