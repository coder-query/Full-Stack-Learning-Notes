package com.shuai.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl {
    @SentinelResource("getProducts")
    public String getProducts() {
        return "获取商品信息。。。";
    }
}
