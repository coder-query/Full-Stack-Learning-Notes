package com.it.service_product.service;

import com.it.service_common.pojo.Products;
import com.it.service_product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductMapper productMapper;

    /**
     * 根据商品ID查询商品对象
     */
    @Override
    public Products findById(Integer productId) {
        return productMapper.selectById(productId);
    }
}