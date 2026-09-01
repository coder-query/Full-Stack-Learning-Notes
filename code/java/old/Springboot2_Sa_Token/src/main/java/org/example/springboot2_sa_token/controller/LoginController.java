package org.example.springboot2_sa_token.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录测试 
 */
@RestController
@RequestMapping("/acc/")
public class LoginController {

    // 测试登录  ---- http://localhost:8081/acc/doLogin?name=zhang&pwd=123456
    @RequestMapping(value = "doLogin",method = {RequestMethod.POST,RequestMethod.GET})
    public SaResult doLogin(String name, String pwd) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对 
        if("zhang".equals(name) && "123456".equals(pwd)) {
            StpUtil.login(10001);
            return SaResult.ok("登录成功");
        }
        return SaResult.error("登录失败");
    }

    // 查询登录状态  ---- http://localhost:8081/acc/isLogin
    @RequestMapping(value = "isLogin",method = {RequestMethod.POST,RequestMethod.GET})
    public SaResult isLogin() {
       // 获取当前会话是否已经登录，返回true=已登录，false=未登录
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    // 检查 登录状态 ---- http://localhost:8081/acc/checkLogin
    @RequestMapping(value = "checkLogin",method = {RequestMethod.POST,RequestMethod.GET})
    public SaResult checkLogin() {
        // 检验当前会话是否已经登录, 如果未登录，则抛出异常：`NotLoginException`
        StpUtil.checkLogin();
        return SaResult.ok("已登录");
    }

    
    // 查询 Token 信息  ---- http://localhost:8081/acc/tokenInfo
    @RequestMapping(value = "tokenInfo",method = {RequestMethod.POST,RequestMethod.GET})
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }
    
    // 测试注销  ---- http://localhost:8081/acc/logout

    @RequestMapping(value = "logout",method = {RequestMethod.POST,RequestMethod.GET})
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }
    
}