package com.shuai.cloud.provider.controller;

import com.commons.enums.ReturnCodeEnum;
import com.commons.model.dto.PayDTO;
import com.commons.model.entity.Pay;
import com.commons.resp.ResultData;
import com.shuai.cloud.provider.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

    /**
     * 增
     *
     * @param pay
     * @return
     */

    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增", description = "新增支付流水方法,json串做参数")
    public ResultData<String> addPay(@RequestBody Pay pay) {
        System.out.println("新增-> " + pay.toString());
        int i = payService.add(pay);
        return ResultData.success("成功插入记录，返回值：" + i);
    }

    /**
     * 删
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/pay/del/{id}")
    @Operation(summary = "删除", description = "删除支付流水方法")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id) {
        System.out.println("删除-> " + id.toString());
        int i = payService.delete(id);
        return ResultData.success(i);
    }

    /**
     * 改
     *
     * @param payDTO
     * @return
     */
    @PutMapping(value = "/pay/update")
    @Operation(summary = "修改", description = "修改支付流水方法")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
        System.out.println("更新-> " + payDTO.toString());
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);

        int i = payService.update(pay);
        return ResultData.success("成功修改记录，返回值：" + i);
    }

    /**
     * 查 单条
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "按照ID查流水", description = "查询支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
        System.out.println("查询-> " + id.toString());
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    /**
     * 查 多条
     *
     * @return
     */
    @GetMapping(value = "/pay/getAll")
    @Operation(summary = "查询所有支付流水", description = "查询所有的支付流水方法")
    public ResultData<List<Pay>> getAll() {
//        int num = 10 / 0;
        List<Pay> payList = payService.getAll();
        System.out.println("查询所有-> " + payList.toString());
        return ResultData.success(payList);
    }

    /**
     * 自定义异常处理方式 测 试
     *
     * @return
     */
    @GetMapping(value = "/pay/error")
    public ResultData<Integer> getPayError() {
        Integer i = Integer.valueOf(200);
        try {
            System.out.println("--------come here");
            int data = 10 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return ResultData.success(i);
    }


}