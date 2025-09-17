package com.it.controller;


import com.it.pojo.stock.Product;
import com.it.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: 心动的offer->13k
 * @Author 帅宏编码-coding
 * @Date 2025/1/31 星期五 12:52
 */
@RestController
@RequestMapping("/stock")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/getProduct/{id}")
    public Product getProduct(@PathVariable("id") Integer id){
        Product product = productService.getProductById(id);
        System.out.println("product = "+product);
        return product;
    }
}
