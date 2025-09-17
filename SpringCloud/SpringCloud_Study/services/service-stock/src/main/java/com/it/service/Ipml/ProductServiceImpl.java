package com.it.service.Ipml;

import ch.qos.logback.core.util.TimeUtil;
import com.it.pojo.stock.Product;
import com.it.service.ProductService;
import io.micrometer.core.instrument.util.TimeUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @Title: 心动的offer->13k
 * @Author 帅宏编码-coding
 * @Date 2025/1/31 星期五 13:55
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Override
    public Product getProductById(Integer id) {
        Product product = new Product();
        product.setId(id);
        product.setProductName("华为手机--" + id);
        product.setProductNum(3);
        product.setProductPrice(new BigDecimal("1000"));

        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return product;
    }
}
