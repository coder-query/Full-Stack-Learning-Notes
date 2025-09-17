package com.commons.apis;

import com.commons.model.dto.PayDTO;
import com.commons.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/7 0007
 */
@FeignClient("consul-payment-service")
public interface PayOpenFeignApi {
    /**
     * 新增一条支付相关流水记录
     *
     * @param payDTO
     * @return
     */
    @PostMapping("/pay/add")
    ResultData addPay(@RequestBody PayDTO payDTO);

    /**
     * 按照主键记录查询支付流水信息
     *
     * @param id
     * @return
     */
    @GetMapping("/pay/get/{id}")
    ResultData getPayInfo(@PathVariable("id") Integer id);

    /**
     * openfeign天然支持负载均衡演示
     *
     * @return
     */
    @GetMapping(value = "test/loadBalance")
    String myLoadBalance();
}