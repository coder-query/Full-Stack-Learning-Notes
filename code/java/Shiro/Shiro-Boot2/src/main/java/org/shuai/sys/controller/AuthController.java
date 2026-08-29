package org.shuai.sys.controller;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.shuai.response.Response;
import org.shuai.sys.model.entity.SysUser;
import org.shuai.sys.service.UserService;
import org.shuai.sys.shiro.utils.JwtUtils;
import org.shuai.sys.shiro.utils.ShiroHashUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/auth")
@Api(tags = "认证接口（登录、注册、退出等）")
public class AuthController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "登录接口")
    @PostMapping(value = "/login")
    public Response<String> login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password
    ){

        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            throw new UnknownAccountException("用户名或密码不能为空");
        }

        // 查询数据库
        SysUser sysUser = userService.getUserByUsername(username);

        if (Objects.isNull(sysUser)){
            log.error("用户名或密码错误,请重新输入");
            throw new UnknownAccountException("用户名或密码错误,请重新输入");
        }

        // 校验密码
        if (!ShiroHashUtils.verifyWithSha256(password, sysUser.getPassword())){
            log.error("用户名或密码错误,请重新输入");
            throw new IncorrectCredentialsException("用户名或密码错误,请重新输入");
        }
        // 上面如果没有抛出任何异常，则说明登录校验完成，生成jwt
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(JwtUtils.USER_ID, sysUser.getId());
        claims.put(JwtUtils.USERNAME, sysUser.getUsername());
        return Response.success(JwtUtils.generateToken(claims));

    }
}
