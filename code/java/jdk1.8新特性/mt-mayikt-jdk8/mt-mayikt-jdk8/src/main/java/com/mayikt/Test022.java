package com.mayikt;

import com.mayikt.entity.OrderEntity;

import java.util.Optional;
import java.util.function.Function;

/**
 * @ClassName Test022
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class Test022 {
    public static void main(String[] args) {
        String orderName = Test022.getOrderName();
        System.out.println(orderName);
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
        //获取我们对象中  orderNameOptional
//        Optional<OrderEntity> orderOptional = Optional.ofNullable(order);
//        Optional<String> orderNameOptional = orderOptional.map(orderEntity -> orderEntity.getOrderName());
//        Optional<String> toLowerCase = orderNameOptional.map(name ->
//                name.toLowerCase());
        return Optional.ofNullable(order).map(orderEntity -> orderEntity.getOrderName()).map(orderName ->
                orderName.toLowerCase()).orElse(null);
    }

}
