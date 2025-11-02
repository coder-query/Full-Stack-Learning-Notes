package com.shuai.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${target.service.name}")
public interface ConsumerFeignService {
    @RequestMapping(value = "/helloNacosProvider", method = RequestMethod.GET)
    String helloNacosProvider();
}
