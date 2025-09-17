package com.shaui.spring_security.service;

import com.shaui.spring_security.model.dto.LoginUserDTO;
import com.shaui.spring_security.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author zsh
 * @description 针对表【user】的数据库操作Service
 * @createDate 2025-06-13 13:45:36
 */
public interface UserService extends IService<User> {

    // 用户登录 登录成功返回 jwt 令牌
    String login(LoginUserDTO loginUserDTO);

    // 退出登录 , 删除Redis缓存中的用户信息
    boolean logout();
}
