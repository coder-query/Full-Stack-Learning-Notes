package com.example.spring_security_study.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.spring_security_study.common.enums.RoleEnums;
import com.example.spring_security_study.mapper.user.UserMapper;
import com.example.spring_security_study.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/12 0012
 */
@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {
    @Resource
    UserMapper userMapper;

    @Override
    public void createUser(UserDetails userDetails) {
        User newUser = new User();
        newUser.setUsername(userDetails.getUsername());
        newUser.setPassword(userDetails.getPassword());
        newUser.setEnabled(true);
        newUser.setRole(0);
        userMapper.insert(newUser);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    /**
     * 根据username 从数据库加载用户信息
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("用户名为空~");
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        // 数据库方面对username 申明了唯一索引,所以根据username查询,只会查出一条数据
        User user = userMapper.selectOne(queryWrapper);

        if (user == null) {
            throw new UsernameNotFoundException(username + "此用户不存在");
        } else {
//            return new org.springframework.security.core.userdetails.User(
//                    user.getUsername(),
//                    user.getPassword(),
//                    user.getEnabled(),
//                    true, //用户账号是否过期
//                    true, //用户凭证是否过期
//                    true, //用户是否未被锁定
//                    AuthorityUtils.commaSeparatedStringToAuthorityList("")); //权限列表
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    // 根据数据库的role字段 进行枚举值匹配 赋予权限
                    .roles(RoleEnums.getValue(user.getRole()).getDesc())
                    .build();
        }
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }
}
