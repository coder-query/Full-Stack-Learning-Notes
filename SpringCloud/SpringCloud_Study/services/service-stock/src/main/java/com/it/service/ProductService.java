package com.it.service;

import com.it.pojo.stock.Product;

/**
 * @Title: 心动的offer->13k
 * @Author 帅宏编码-coding
 * @Date 2025/1/31 星期五 13:55
 */
public interface ProductService {
    // 根据id查询商品
    Product getProductById(Integer id);
}
