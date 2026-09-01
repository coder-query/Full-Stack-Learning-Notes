package com.shuai.controller;

import com.alibaba.fastjson2.JSON;
import com.mybatisflex.core.query.QueryMethods;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateWrapper;
import com.shuai.mapper.UserMapper;
import com.shuai.model.User;
import com.shuai.model.table.UserTableDef;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/all")
    @ApiOperation("获取所有用户")
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    @GetMapping("/groupBy")
    @ApiOperation("分组测试")
    public String groupBy() {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(QueryMethods.groupConcat(UserTableDef.USER.NAME))
                .from(UserTableDef.USER)
                .groupBy(UserTableDef.USER.USER_PASSWORD);
        return JSON.toJSONString(userMapper.selectObjectListByQuery(queryWrapper));
    }
}
