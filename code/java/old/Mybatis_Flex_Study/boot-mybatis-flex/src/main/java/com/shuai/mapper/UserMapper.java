package com.shuai.mapper;

import com.mybatisflex.core.BaseMapper;
import com.shuai.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {}
