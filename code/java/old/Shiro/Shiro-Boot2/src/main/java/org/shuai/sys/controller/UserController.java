package org.shuai.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.shuai.sys.model.entity.SysUser;
import org.shuai.sys.service.UserService;
import org.shuai.sys.shiro.utils.ShiroHashUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "新增用户接口")
    @PostMapping(value = "/add")
    @RequiresPermissions(value = "sys:user:add")
    public String addUser(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password
    ) {
        SysUser sysUser = SysUser.builder()
                .username(username)
                .password(ShiroHashUtils.encryptWithSha256(password))
                .salt(ShiroHashUtils.SALT)
                .status("1")
                .build();
        userService.addUser(sysUser);
        return "添加用户完成!!!";
    }

    @ApiOperation(value = "查询用户接口")
    @GetMapping(value = "/select/{id}")
    @RequiresPermissions(value = "sys:user:select")
    public SysUser selectUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @ApiOperation(value = "删除用户接口")
    @DeleteMapping(value = "/delete/{id}")
    @RequiresPermissions(value = "sys:user:delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "删除用户完成!!!";
    }

    @ApiOperation(value = "更新用户接口")
    @PutMapping(value = "/update")
    @RequiresPermissions(value = "sys:user:update")
    public String updateUser(@RequestBody SysUser sysUser) {
        userService.updateUser(sysUser);
        return "更新用户完成!!!";
    }
}
