package com.shuai.cloud.consumer.controller;

import com.commons.model.dto.PayDTO;
import com.commons.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class OrderController {
    //public static final String PaymentSrv_URL = "http://localhost:8001";//先写死，硬编码

    @Value("${zsh.consul.provider-name}")
    public static String PaymentSrv_URL; //服务注册中心上的微服务名称

    @Resource
    private RestTemplate restTemplate;

    /**
     * 一般情况下，通过浏览器的地址栏输入url，发送的只能是get请求
     * 我们模拟消费者发送get请求，but底层调用post方法，客户端消费者参数PayDTO可以不添加@RequestBody
     *
     * @param payDTO
     * @return
     */
    @GetMapping("/consumer/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add", payDTO, ResultData.class);
    }

    // 删除+修改操作作为家庭作业，O(∩_∩)O。。。。。。。

    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfoById(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/" + id, ResultData.class, id);
    }

    @GetMapping("/consumer/pay/getAll")
    public ResultData getAllPayInfo() {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/getAll", ResultData.class);
    }
}