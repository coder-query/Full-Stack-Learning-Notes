package org.shuai.boot.shirocodestudy.sys.controller;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.shuai.boot.shirocodestudy.sys.shiro.utils.JwtUtils;
import org.shuai.boot.shirocodestudy.sys.shiro.utils.ShiroUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
@Api(tags = "认证接口（登录、注册、退出等）")
public class AuthController {

    @ApiOperation(value = "登录接口")
    @PostMapping(value = "/login")
    public String login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password
    ){
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return "username or password is empty";
        }
        try{
            ShiroUtils.login(username, password);
        }catch (UnknownAccountException e){
            log.error("账号名不正确, e = ", e);
            return "username or password is error";
        }catch (IncorrectCredentialsException e){
            log.error("密码不正确, e = ", e);
            return "username or password is error";
        }
        // 上面如果没有抛出任何异常，则说明登录校验完成，生成jwt
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        return JwtUtils.generateToken(claims);
//        return "login success";
    }
}
