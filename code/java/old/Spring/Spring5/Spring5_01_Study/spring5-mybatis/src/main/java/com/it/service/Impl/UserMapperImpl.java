package com.it.service.Impl;

import com.it.service.UserMapper;
import com.it.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserMapperImpl implements UserMapper {

    @Autowired
    private SqlSession sqlSession;


    @Override
    public List<User> getUserList() {
        return sqlSession.getMapper(UserMapper.class).getUserList();
    }


}
