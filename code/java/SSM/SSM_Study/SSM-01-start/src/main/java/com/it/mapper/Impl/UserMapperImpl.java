package com.it.mapper.Impl;

import com.it.mapper.UserMapper;
import com.it.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserMapperImpl implements UserMapper {


    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<User> getUserList() {
        System.out.println("sqlSession拿到了....");
        return sqlSession.getMapper(UserMapper.class).getUserList();
    }


}
