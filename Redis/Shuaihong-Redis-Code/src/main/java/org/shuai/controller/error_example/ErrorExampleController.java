package org.shuai.controller.error_example;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.shuai.entity.Stock;
import org.shuai.mapper.StockMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;


@Slf4j
@RestController
@RequestMapping("/error-example")
@Api(tags = "本地单机锁（在分布式下的错误示例）")
public class ErrorExampleController {

    @Resource
    private StockMapper stockMapper;

    /**
     * 本地锁
     */
    private final static ReentrantLock localCasLock = new ReentrantLock(false);

    @GetMapping("/deduct-stock")
    @ApiOperation(value = "扣减库存")
    public String deductStock(@RequestParam(value = "productId") Integer productId) {
        try {

            // 获取锁
            localCasLock.lock();
            log.info("本地锁获取成功 for product ID: {} ，线程名称：{}，线程ID：{}", productId, Thread.currentThread().getName(), Thread.currentThread().getId());

            // 扣减库存
            Map<String, Object> resultMap = deductStockSync(productId);

            // 返回结果
            return (String) (resultMap.get("msg"));
        } catch (Exception e) {
            log.error("Error occurred while deducting stock: {}", e.getMessage());
        } finally {
            // 释放锁
            localCasLock.unlock();
            log.info("本地锁释放成功 for product ID: {} ，线程名称：{}，线程ID：{}", productId, Thread.currentThread().getName(), Thread.currentThread().getId());
        }
        return "库存扣减失败";
    }

    public Map<String,Object> deductStockSync(Integer productId){

        // 查询库存
        Stock stock = stockMapper.selectOne(Wrappers.<Stock>lambdaQuery().eq(Stock::getId, productId));
        if (Objects.isNull(stock) || stock.getProductCount() <= 0){
            log.error("库存不足 for product ID: {} ，线程名称：{}，线程ID：{}", productId, Thread.currentThread().getName(), Thread.currentThread().getId());
            return Collections.singletonMap("msg", "库存不足");
        }

        // 原库存
        int oldStock = stock.getProductCount();
        // 扣减后库存
        int newStock = oldStock - 1;

        // 扣减库存
        int updateFlag = stockMapper.update(null, Wrappers.<Stock>lambdaUpdate()
                .set(Stock::getProductCount, newStock)
                .eq(Stock::getId, productId));

        if (updateFlag <= 0){
            log.error("库存扣减失败 for product ID: {} ，线程名称：{}，线程ID：{}", productId, Thread.currentThread().getName(), Thread.currentThread().getId());
            return Collections.singletonMap("msg", "库存扣减失败");
        }

        log.info("库存扣减成功 for product ID: {} ，线程名称：{}，线程ID：{}, 原库存：{}，扣减后库存剩余：{}", productId, Thread.currentThread().getName(), Thread.currentThread().getId(), oldStock, newStock);
        return Collections.singletonMap("msg", "库存扣减成功，原库存：" + oldStock + "，扣减后库存剩余：" + newStock);
    }

    /**
     * 获取数据库中所有的商品信息
     */
    @GetMapping("/get-all-stock")
    @ApiOperation(value = "获取数据库中所有的商品信息")
    public List<Stock> getAllStock() {
        return stockMapper.selectList(Wrappers.emptyWrapper());
    }

    /**
     * 恢复所有商品数量为100
     */
    @GetMapping("/restore-stock")
    @ApiOperation(value = "恢复所有商品数量为100")
    public String restoreStock() {
        stockMapper.update(null, Wrappers.<Stock>lambdaUpdate()
                        .set(Stock::getProductCount, 100)
                        .ne(Stock::getProductCount, -66666));
        return "库存恢复成功";
    }
}
