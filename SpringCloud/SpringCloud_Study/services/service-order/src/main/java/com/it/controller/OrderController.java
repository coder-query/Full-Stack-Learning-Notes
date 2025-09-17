package com.it.controller;

import com.it.pojo.order.Order;
import com.it.properties.OrderProperties;
import com.it.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @Title: 心动的offer->13k
 * @Author 帅宏编码-coding
 * @Date 2025/1/31 星期五 0:20
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderProperties orderProperties;

    @RequestMapping("/createOrder")
    public Order addOrder(@RequestParam("productId") Integer productId,
                          @RequestParam("userId") Integer userId){
        Order order = orderService.createOrder(productId, userId);
        System.out.println("order = " + order);
        return order;
    }
    @RequestMapping("/config")
    public String getConfig(){
        return "    order.timeout: "+orderProperties.getTimeout() +
                "   order.maxSize: "+orderProperties.getMaxSize() +
                "   order.dataUrl: " + orderProperties.getDataUrl();
    }
}
