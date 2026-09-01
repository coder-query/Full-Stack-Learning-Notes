package com.shuai.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${target.server.name}",contextId = "productFeignClient")
public interface ProductFeignClient {
    @RequestMapping("/product/getProductById")
    String getProductById(@RequestParam("id") Integer id);

}
