package com.example.spring_security_study.service;

import com.example.spring_security_study.model.dto.UserDTO;
import com.example.spring_security_study.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author zsh
 * @description 针对表【t_user】的数据库操作Service
 * @createDate 2025-06-12 00:39:57
 */
public interface UserService extends IService<User> {
    void saveUserDetails(UserDTO userDTO);
}
