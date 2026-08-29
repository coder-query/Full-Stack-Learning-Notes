package com.shuai.fallback;

import com.shuai.feign.ConsumerFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerOrderFallBackFactory implements FallbackFactory<ConsumerFeignService> {
    @Override
    public ConsumerFeignService create(Throwable cause) {
        return new ConsumerFeignService() {
//            @Override
//            public String helloNacosProvider() {
//                log.error("helloNacosProvider 调用失败：{}", cause.getMessage());
//                return "helloNacosProvider 接口请求异常，请稍后测试..";
//            }

            @Override
            public String queryOrder() {
                log.error("queryOrder 调用失败：{}", cause.getMessage());
                return "queryOrder 订单查询接口请求异常，请稍后测试..";
            }

            @Override
            public String updateOrder() {
                log.error("updateOrder 调用失败：{}", cause.getMessage());
                return "updateOrder 订单更新接口请求异常，请稍后测试..";
            }

//            @Override
//            public String addOrder() {
//                log.error("addOrder 调用失败：{}", cause.getMessage());
//                return "addOrder 订单添加接口请求异常，请稍后测试..";
//            }
        };
    }
}
