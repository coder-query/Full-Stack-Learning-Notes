package com.it.mappper;

import com.it.mapper.UserMapper;
import com.it.pojo.User;
import com.it.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapperTest {

//    传入一个name模糊查询
    @Test
    public void getUserListByNameTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "帅宏");
        List<User> userList = userMapper.getUserList(map);
        for (User user : userList) {
            System.out.println(user);
        }
        if (sqlSession != null) sqlSession.close();
    }

    //传入1个id查询用户
    @Test
    public void getUserByIdTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        List<User> userList = userMapper.getUserList(map);
        for (User user : userList) {
            System.out.println(user);
        }
        if (sqlSession != null) sqlSession.close();
    }

}
