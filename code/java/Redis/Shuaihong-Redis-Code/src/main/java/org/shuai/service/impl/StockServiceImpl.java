package org.shuai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.shuai.service.StockService;
import org.shuai.mapper.StockMapper;
import org.shuai.entity.Stock;
import org.springframework.stereotype.Service;

/**
* @author 27986
* @description 针对表【t_stock(库存表)】的数据库操作Service实现
* @createDate 2026-04-21 21:31:36
*/
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService{

}




