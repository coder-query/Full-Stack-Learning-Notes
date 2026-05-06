package org.shuai.boot.shirocodestudy.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.shuai.boot.shirocodestudy.sys.service.UserService;
import org.shuai.boot.shirocodestudy.sys.shiro.entity.SysUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "新增用户接口")
    @PostMapping(value = "/add")
    @RequiresPermissions(value = "sys:user:add")
    public String addUser(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password
    ){
        String s = userService.addUser(username, password);
        return "添加用户完成!!!";
    }
    /**
     * 获取用户信息
     */
    @ApiOperation(value = "查询用户接口")
    @PostMapping(value = "/select")
    @RequiresPermissions(value = "sys:user:select")
    public SysUser selectUser(){
        return SysUser.builder()
                .username("wdwefwef")
                .status("1")
                .build();
    }

}
