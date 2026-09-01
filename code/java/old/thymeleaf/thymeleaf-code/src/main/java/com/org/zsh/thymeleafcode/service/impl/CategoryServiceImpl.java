package com.org.zsh.thymeleafcode.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.zsh.thymeleafcode.model.entity.Category;
import com.org.zsh.thymeleafcode.service.CategoryService;
import com.org.zsh.thymeleafcode.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 27986
* @description 针对表【category(图书分类表)】的数据库操作Service实现
* @createDate 2026-05-18 02:47:48
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




