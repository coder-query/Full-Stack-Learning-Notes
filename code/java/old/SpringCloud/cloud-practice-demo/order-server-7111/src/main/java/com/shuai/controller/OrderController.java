package com.shuai.controller;

import com.alibaba.fastjson.JSON;
import com.shuai.feign.ProductFeignClient;
import com.shuai.model.Order;
import com.shuai.model.OrderVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource private ProductFeignClient productFeignClient;
    @RequestMapping("/getOrderById")
    public String getOrder(@RequestParam("id") Integer id){
        Order order = new Order();
        order.setId(id);
        order.setName("mayikt");
        order.setPrice("100");
        order.setStatus("1");
        order.setCreateTime("2020-05-05");
        order.setUpdateTime("2020-05-05");
        System.out.println("order server 被调用 ： 订单id" + id + " 订单信息 ： " + order);
        String product = productFeignClient.getProductById(id);
        System.out.println("product server 被调用 ： 订单id" + id + " 商品信息 ： " + product);
        OrderVo orderVo = new OrderVo(order, List.of(product));
        return JSON.toJSONString(orderVo);
    }
}
