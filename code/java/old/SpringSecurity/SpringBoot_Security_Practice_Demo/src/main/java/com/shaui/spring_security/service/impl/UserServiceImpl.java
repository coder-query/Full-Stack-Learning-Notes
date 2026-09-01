package com.shaui.spring_security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shaui.spring_security.common.StringCommons;
import com.shaui.spring_security.model.dto.LoginUserDTO;
import com.shaui.spring_security.model.entity.LoginUserDetails;
import com.shaui.spring_security.model.entity.User;
import com.shaui.spring_security.service.UserService;
import com.shaui.spring_security.mapper.UserMapper;
import com.shaui.spring_security.utils.JwtUtils;
import com.shaui.spring_security.utils.RedisCacheUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author zsh
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2025-06-13 13:45:36
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private ProviderManager providerManager;

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private RedisCacheUtils redisCacheUtils;

    /**
     * // 用户登录 登录成功返回 jwt 令牌
     *
     * @return jwt令牌
     */
    @Override
    public String login(LoginUserDTO loginUserDTO) {

        // 对前端用户输入的账号密码进行封装成 Authentication 对象

        UsernamePasswordAuthenticationToken authenticationUser = new UsernamePasswordAuthenticationToken(loginUserDTO.getUsername(), loginUserDTO.getPassword());

        // authenticationManager.authenticate(authenticationUser); 底层自动调用 loadUserByUsername()
        // 从数据库加载真实用户信息, 再俩者比较 返回 Authentication
        // 如果验证通过 , 这个对象里面就保存用户相关的权限信息
        // 如果验证没通过, 这个对象里面是null
        Authentication authenticate = providerManager.authenticate(authenticationUser);

        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户账号或密码输入的不正确~");
        }

        // 上面验证通过后 生成jwt令牌
        // 将 <用户的id + 用户的username> 封装在jwt的负载中
        LoginUserDetails loginUserDetails =
                (LoginUserDetails) authenticate.getPrincipal();

        System.out.println("authenticate.getPrincipal() ---> " + authenticate.getPrincipal());
        Long id = loginUserDetails.getUser().getId().longValue();
        String username = loginUserDetails.getUser().getUsername();

        // 把完整的用户信息存入Redis userId作为key
        redisCacheUtils.setCacheObject(StringCommons.REDIS_LOGIN_KEY + id, loginUserDetails);

        // 返回jwt令牌
        return jwtUtils.createToken(id, username);
    }

    /**
     * 退出登录
     *
     * @return 返回boolean
     */
    @Override
    public boolean logout() {

        // 从SecurityContextHolder上下文去获取Authentication对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 根据userId删除Redis中的用户信息
        LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
        long id = loginUserDetails.getUser().getId().longValue();
        return redisCacheUtils.deleteObject(StringCommons.REDIS_LOGIN_KEY + id);

    }
}




