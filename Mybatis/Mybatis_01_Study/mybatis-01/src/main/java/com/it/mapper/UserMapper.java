package com.it.mapper;

import com.it.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    //查询用户(注解查询)
    @Select("select * from user where id = #{id}")
    List<User> getUserListByAnnotation(@Param("id") int id);

    //查询所有用户
    List<User> getUserList();

    //根据id查询指定用户
    User getUserById(int id);

    //添加用户
    int addUser(User user);

    //修改用户
    int updateUser(User user);

    //删除用户
    int deleteUser(int id);

}
