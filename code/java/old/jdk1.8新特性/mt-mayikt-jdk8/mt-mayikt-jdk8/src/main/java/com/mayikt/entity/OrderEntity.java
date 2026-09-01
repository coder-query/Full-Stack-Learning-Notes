package com.mayikt.entity;

/**
 * @ClassName OrderEntity
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
public class OrderEntity {
    private String orderId;
    private String orderName;
    private String orderDes;

    public OrderEntity(String orderId, String orderName) {
        this.orderId = orderId;
        this.orderName = orderName;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getOrderDes() {
        return orderDes;
    }
}
