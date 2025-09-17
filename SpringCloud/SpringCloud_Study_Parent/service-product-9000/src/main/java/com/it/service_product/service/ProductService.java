package com.it.service_product.service;

import com.it.service_common.pojo.Products;

public interface ProductService {
    Products findById(Integer productId);
}
