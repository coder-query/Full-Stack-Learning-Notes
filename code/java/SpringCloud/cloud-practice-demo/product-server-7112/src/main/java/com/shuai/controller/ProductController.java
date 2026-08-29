package com.shuai.controller;

import com.alibaba.fastjson.JSON;
import com.shuai.model.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @RequestMapping("/getProductById")
    public String getProductById(@RequestParam("id") Integer id){
        Product product = new Product();
        product.setId(id);
        product.setName("iphone 7112");
        product.setPrice("￥138");
        System.out.println("Product server 7112 被调用 ： 订单id" + id + " 商品信息 ： " + product);
        return JSON.toJSONString(product);
    }
}
