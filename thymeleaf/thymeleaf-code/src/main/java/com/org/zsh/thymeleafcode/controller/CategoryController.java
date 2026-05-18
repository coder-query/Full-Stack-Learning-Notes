package com.org.zsh.thymeleafcode.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.org.zsh.thymeleafcode.mapper.CategoryMapper;
import com.org.zsh.thymeleafcode.model.dto.CategoryPageDTO;
import com.org.zsh.thymeleafcode.model.entity.Category;
import com.org.zsh.thymeleafcode.model.response.PageResult;
import com.org.zsh.thymeleafcode.model.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "分类管理")
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryMapper categoryMapper;

    @PostMapping("/page")
    @ResponseBody
    @ApiOperation(value = "分页查询分类列表")
    public Response<PageResult<Category>> page(CategoryPageDTO categoryPageDTO){
        PageHelper.startPage(categoryPageDTO.getPageNo(), categoryPageDTO.getPageSize());
       List<Category> categoryPageList = categoryMapper.categoryPageList(Maps.newHashMap());
       PageInfo<Category> pageInfo = new PageInfo<>(categoryPageList);
        return Response.page(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPageSize());
    }
}
