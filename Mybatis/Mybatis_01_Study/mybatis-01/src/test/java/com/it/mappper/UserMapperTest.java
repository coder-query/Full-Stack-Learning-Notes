package com.it.mappper;

import com.it.mapper.UserMapper;
import com.it.pojo.User;
import com.it.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserMapperTest {


    @Test
    public void getUserListTest() {
            SqlSession sqlSession = MybatisUtils.getSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.getUserList();
            for (User user : userList) {
                System.out.println(user);
            }
            if(sqlSession != null) sqlSession.close();
    }
    @Test
    public void getUserListByAnnotationTest() {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.getUserListByAnnotation(1);
        for (User user : userList) {
            System.out.println(user);
        }
        if(sqlSession != null) sqlSession.close();
    }

    @Test
    public void getUserByIdTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserById(1);
        System.out.println(user);
        if(sqlSession != null) sqlSession.close();
    }

    @Test
    public void addUserTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User(4,"小笨蛋","123123456");
        int res = userMapper.addUser(user);
        if(res > 0) System.out.println(user.getName()+"的用户信息添加成功");
        sqlSession.commit();
        if(sqlSession != null) sqlSession.close();
    }

    @Test
    public  void  deleteUserById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        String user_name = userMapper.getUserById(7).getName();
        int res = userMapper.deleteUser(7);
        if(res > 0) System.out.println(user_name + "删除成功");
        sqlSession.commit();
        if(sqlSession != null) sqlSession.close();
    }
}
