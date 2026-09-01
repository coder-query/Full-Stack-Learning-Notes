package com.anxiang.mybatisspring.service;

import com.anxiang.mybatisspring.mapper.GoodsMapper;
import com.anxiang.mybatisspring.model.Goods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 类功能：
 *
 * @author shuaihong-coding
 * @date 2026-01-14 18:55
 */
@Service
public class BusinessService {

    @Resource
    private GoodsMapper goodsMapper;

    public List<Goods> getAllGoods() {
//        System.out.println("goodsMapper.hashCode()===>"+goodsMapper.hashCode());
        return goodsMapper.selectAll();
    }
}
