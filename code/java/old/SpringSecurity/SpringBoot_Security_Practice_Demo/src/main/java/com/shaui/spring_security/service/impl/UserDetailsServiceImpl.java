package com.shaui.spring_security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shaui.spring_security.mapper.UserMapper;
import com.shaui.spring_security.model.entity.LoginUserDetails;
import com.shaui.spring_security.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/13 0013
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据 username 从数据库 查询 用户信息 封装到 user 中
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, username);

        User user = userMapper.selectOne(userLambdaQueryWrapper);
        System.out.println("// 根据 username 从数据库 查询 用户信息 封装到 user 中");
        // 对user 进行判空
        // 如果为空,说明用户没找到 抛出 UsernameNotFoundException异常
        if (Objects.isNull(user)) {
            System.out.println("user 为空~");
            throw new UsernameNotFoundException("用户账号不存在~");
        }

        // 如果找到了那就将user 封装到 User 中
        System.out.println("// 如果找到了那就将user 封装到 LoginUserDetails 中");
        List<String> permissionList = new ArrayList<>(Arrays.asList("test", "admin", "prod", "dev"));
        return new LoginUserDetails(user, permissionList);
    }
}
