package com.org.zsh.thymeleafcode.controller;

import com.google.common.collect.Maps;
import com.org.zsh.thymeleafcode.mapper.CategoryMapper;
import com.org.zsh.thymeleafcode.model.entity.Category;
import com.org.zsh.thymeleafcode.response.Response;
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
    public Response<List<Category>> page(){
        return Response.success(categoryMapper.categoryPageList(Maps.newHashMap()));
    }
}
