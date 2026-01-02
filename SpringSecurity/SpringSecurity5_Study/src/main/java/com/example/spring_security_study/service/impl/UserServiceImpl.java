package com.example.spring_security_study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.spring_security_study.model.dto.UserDTO;
import com.example.spring_security_study.model.entity.User;
import com.example.spring_security_study.config.security.DBUserDetailsManager;
import com.example.spring_security_study.service.UserService;
import com.example.spring_security_study.mapper.user.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author zsh
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2025-06-12 00:39:57
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Resource
    private DBUserDetailsManager dbUserDetailsManager;

    @Override
    public void saveUserDetails(UserDTO userDTO) {
        Collection<? extends GrantedAuthority> authoritiesList = new ArrayList<>(); // 临时创建空权限集合
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withDefaultPasswordEncoder()
                .username(userDTO.getUsername()) //自定义用户名
                .password(userDTO.getPassword()) //自定义密码
                .authorities(authoritiesList)
                .build();
        dbUserDetailsManager.createUser(userDetails);
    }
}




