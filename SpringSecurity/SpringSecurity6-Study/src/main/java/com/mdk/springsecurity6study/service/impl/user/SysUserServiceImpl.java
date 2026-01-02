package com.mdk.springsecurity6study.service.impl.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdk.springsecurity6study.common.entity.SysUserEntity;
import com.mdk.springsecurity6study.mapper.SysUserMapper;
import com.mdk.springsecurity6study.service.api.SysUserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
* @author 27986
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2026-01-02 19:33:49
*/
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

    private final AuthenticationManager authenticationManager;


    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        Object details = authenticate.getPrincipal();
        if (details instanceof SysUserEntity sysUserEntity) {
            log.error("sysUserEntity =>>> "+ sysUserEntity);
        }
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}




