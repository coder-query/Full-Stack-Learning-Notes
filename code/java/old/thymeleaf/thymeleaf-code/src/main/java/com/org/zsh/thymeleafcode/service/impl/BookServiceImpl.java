package com.org.zsh.thymeleafcode.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.org.zsh.thymeleafcode.model.entity.Book;
import com.org.zsh.thymeleafcode.service.BookService;
import com.org.zsh.thymeleafcode.mapper.BookMapper;
import org.springframework.stereotype.Service;

/**
* @author 27986
* @description 针对表【book(图书表)】的数据库操作Service实现
* @createDate 2026-05-18 02:47:48
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
    implements BookService{

}




