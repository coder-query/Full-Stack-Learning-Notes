package com.org.zsh.thymeleafcode.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.org.zsh.thymeleafcode.mapper.BookMapper;;
import com.org.zsh.thymeleafcode.model.dto.BookPageDTO;
import com.org.zsh.thymeleafcode.model.entity.Book;
import com.org.zsh.thymeleafcode.model.response.PageResult;
import com.org.zsh.thymeleafcode.model.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Api(tags = "书籍管理")
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookMapper bookMapper;

    @PostMapping("/page")
    @ResponseBody
    @ApiOperation(value = "分页查询书籍列表")
    public Response<PageResult<Book>> page(BookPageDTO bookPageDTO) {
        PageHelper.startPage(bookPageDTO.getPageNo(), bookPageDTO.getPageSize());
        List<Book> books = bookMapper.bookPageList(new HashMap<>());
        PageInfo<Book> pageInfo = new PageInfo<>(books);
        return Response.page(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPageSize());
    }
}
