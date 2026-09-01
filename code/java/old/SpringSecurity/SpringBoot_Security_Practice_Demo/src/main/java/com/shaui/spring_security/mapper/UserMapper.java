package com.shaui.spring_security.mapper;

import com.shaui.spring_security.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zsh
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2025-06-13 13:45:36
 * @Entity com.shaui.spring_security.model.entity.User
 */
public interface UserMapper extends BaseMapper<User> {
}




