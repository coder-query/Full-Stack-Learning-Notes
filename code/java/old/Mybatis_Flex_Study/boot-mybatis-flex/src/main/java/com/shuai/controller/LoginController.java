package com.shuai.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSON;
import com.shuai.model.User;
import com.shuai.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Objects;

/// **
// * @author : 帅宏-coding
// * @version : 1.0
// * @date : 2025/7/31 0031
// */
@Controller
@RequestMapping(value = "/user")
public class LoginController {

  @Autowired private ILoginService loginService;

  @RequestMapping(value = "login.do", method = RequestMethod.GET)
  @ResponseBody
  public String doLogin(String account, String password) {

    HashMap<String, Object> map = new HashMap<>();
    if (!StringUtils.hasText(account) && !StringUtils.hasText(password)) {
      map.put("status", 1);
      map.put("msg", "账号和密码不能为空~");
      return JSON.toJSONString(map);
    }

    User accountInfo = loginService.login(account, password);

    if (Objects.isNull(accountInfo)) {
      map.put("status", 1);
      map.put("msg", "账号和密码错误~");
      return JSON.toJSONString(map);
    }

    StpUtil.login(accountInfo.getId());
    SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
    map.put("status", 0);
    map.put("msg", "登录成功~");
    map.put("data", accountInfo);
    map.put("tokenInfo", tokenInfo);
    return JSON.toJSONString(map);
  }
}
