package com.shuai.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${target.service.name}",contextId = "testFeignDemo")
public interface TestFeignDemo {
    @RequestMapping("/getMessage")
    String getMessage(@RequestParam(value = "name") String name);
}
