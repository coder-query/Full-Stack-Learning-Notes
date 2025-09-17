package com.it.service;

import com.it.pojo.order.Order;

/**
 * @Title: 心动的offer->13k
 * @Author 帅宏编码-coding
 * @Date 2025/1/31 星期五 14:29
 */
public interface OrderService {
    Order createOrder(Integer productId,Integer userId);
}
