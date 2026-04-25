package org.shuai.controller.error_example;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.shuai.entity.Stock;
import org.shuai.mapper.StockMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
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
    private final static ReentrantLock localCasLock = new ReentrantLock();

    @PostMapping("/deduct-stock")
    @ApiOperation(value = "扣减库存")
    public String deductStock(Integer productId) {
        try {

            // 获取锁
            localCasLock.lock();
            log.info("Lock acquired for product ID: {} ，线程名称：{}，线程ID：{}", productId, Thread.currentThread().getName(), Thread.currentThread().getId());

            // 查询库存
            Stock stock = stockMapper.selectOne(Wrappers.<Stock>lambdaQuery().eq(Stock::getId, productId));
            if (Objects.isNull(stock) || stock.getProductCount() <= 0){
                log.error("Stock not found for product ID: {} ，线程名称：{}，线程ID：{}", productId, Thread.currentThread().getName(), Thread.currentThread().getId());
                return "库存不存在或已售罄";
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
                log.error("Stock deduct failed for product ID: {} ，线程名称：{}，线程ID：{}", productId, Thread.currentThread().getName(), Thread.currentThread().getId());
                return "库存扣减失败";
            }

            log.info("Stock deducted successfully for product ID: {} ，线程名称：{}，线程ID：{}", productId, Thread.currentThread().getName(), Thread.currentThread().getId());
            log.info("原库存：{}，扣减后库存剩余：{}", oldStock, newStock);
            return "库存扣减成功，原库存：" + oldStock + "，扣减后库存剩余：" + newStock;
        } catch (Exception e) {
            log.error("Error occurred while deducting stock: {}", e.getMessage());
        } finally {
            // 释放锁
            localCasLock.unlock();
        }
        return "库存扣减失败";
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
