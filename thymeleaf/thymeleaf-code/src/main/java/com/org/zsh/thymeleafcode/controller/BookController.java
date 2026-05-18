package com.org.zsh.thymeleafcode.controller;

import com.google.common.collect.Maps;
import com.org.zsh.thymeleafcode.mapper.BookMapper;
import com.org.zsh.thymeleafcode.mapper.CategoryMapper;
import com.org.zsh.thymeleafcode.model.dto.BookDTO;
import com.org.zsh.thymeleafcode.model.entity.Book;
import com.org.zsh.thymeleafcode.model.entity.Category;
import com.org.zsh.thymeleafcode.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "书籍管理")
@Controller
@RequestMapping("/category")
public class BookController {

    @Autowired
    private BookMapper bookMapper;

    @PostMapping("/page")
    @ResponseBody
    @ApiOperation(value = "分页查询分类列表")
    public Response<List<Book>> page(BookDTO bookDTO){
        return Response.success(bookMapper.bookPageList(Maps.newHashMap()));
    }
}
