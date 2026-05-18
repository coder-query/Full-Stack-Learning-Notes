package com.org.zsh.thymeleafcode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.org.zsh.thymeleafcode.model.entity.Book;

import java.util.List;
import java.util.Map;

/**
* @author 27986
* @description 针对表【book(图书表)】的数据库操作Mapper
* @createDate 2026-05-18 02:47:48
* @Entity generator.domain.Book
*/
public interface BookMapper extends BaseMapper<Book> {

    List<Book> bookPageList(Map<String, Object> pageMap);

}




