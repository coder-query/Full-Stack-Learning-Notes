package com.it.mapper;

import com.it.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    //动态sql查询用户
    //传入一个map集合
    List<User> getUserList(Map<String, Object> map);


}
