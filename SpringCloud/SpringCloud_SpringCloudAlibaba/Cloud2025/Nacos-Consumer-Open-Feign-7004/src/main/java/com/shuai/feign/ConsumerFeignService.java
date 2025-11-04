package com.shuai.feign;

import com.shuai.fallback.ConsumerOrderFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${target.service.name}", fallbackFactory = ConsumerOrderFallBackFactory.class,contextId = "consumerOrderService")
public interface ConsumerFeignService {

//    @GetMapping("/helloNacosProvider")
//    String helloNacosProvider();

    @GetMapping("/order/query")
    String queryOrder();

    @GetMapping("/order/update")
    String updateOrder();

//    @PostMapping("/order/add")  // 通常添加操作使用 POST
//    String addOrder();
}