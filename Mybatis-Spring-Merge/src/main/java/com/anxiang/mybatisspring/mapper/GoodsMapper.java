package com.anxiang.mybatisspring.mapper;


import com.anxiang.mybatisspring.model.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* @author 27986
* @description 针对表【goods】的数据库操作Mapper
* @createDate 2026-01-14 18:37:41
* @Entity generator.domain.Goods
*/
public interface GoodsMapper {
    List<Goods> selectAll();
}




