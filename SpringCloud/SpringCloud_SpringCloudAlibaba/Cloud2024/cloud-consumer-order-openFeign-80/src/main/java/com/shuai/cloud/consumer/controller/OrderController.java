package com.shuai.cloud.consumer.controller;

import cn.hutool.core.date.DateUtil;
import com.commons.apis.PayOpenFeignApi;
import com.commons.enums.ReturnCodeEnum;
import com.commons.model.dto.PayDTO;
import com.commons.resp.ResultData;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @auther zzyy
 * @create 2023-11-09 15:49
 */
@RestController
@Slf4j
public class OrderController {
    @Resource
    private PayOpenFeignApi payFeignApi;

    @PostMapping("/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        System.out.println("第一步：模拟本地addOrder新增订单成功(省略sql操作)，第二步：再开启addPay支付微服务远程调用");
        ResultData resultData = payFeignApi.addPay(payDTO);
        return resultData;
    }

    @GetMapping("/feign/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");
        ResultData resultData = payFeignApi.getPayInfo(id);
        return resultData;
    }

    /**
     * openfeign天然支持负载均衡演示
     *
     * @return
     */
    @GetMapping(value = "/feign/pay/myLoadBalance")
    public String mylb() {
        String result = null;
        try {
            System.out.println("调用开始-----:" + DateUtil.now());
            result = payFeignApi.myLoadBalance();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用结束-----:" + DateUtil.now());
            ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return result;
    }
}

