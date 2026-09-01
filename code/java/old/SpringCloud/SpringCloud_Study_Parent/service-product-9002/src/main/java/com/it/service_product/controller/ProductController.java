package com.it.service_product.controller;

import com.it.service_common.pojo.Products;
import com.it.service_product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/query/{id}")
    public Products query(@PathVariable Integer id) {
        System.out.println("请求成功... http://localhost:9002//product/query 返回数据...");
        return productService.findById(id);
    }


}