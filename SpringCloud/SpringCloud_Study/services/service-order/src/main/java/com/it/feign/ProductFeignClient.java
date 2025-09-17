package com.it.feign;

import ch.qos.logback.classic.spi.EventArgUtil;
import com.it.pojo.stock.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Title: 心动的offer->13k
 * @Author 帅宏编码-coding
 * @Date 2025/1/31 星期五 21:23
 */
@FeignClient("service-stock")
public interface ProductFeignClient {

    /*
     * 根据id查询商品信息
     *
     */

    @GetMapping("/stock/getProduct/{id}")
    Product getProductById(@RequestParam("id") Integer id);

}
