package com.mdk.springsecurity6study.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.mdk.springsecurity6study.common.entity.SysUser;
import com.mdk.springsecurity6study.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SysUserDetailsService implements UserDetailsService {

    private final SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername=====> 用户名：{}", username);
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(SysUser::getUsername, username)
                .eq(SysUser::getStatus, 0);
        SysUser sysUser = sysUserMapper.selectOne(lambdaQueryWrapper);
        if (ObjectUtils.isNull(sysUser)) {
            log.info("loadUserByUsername=====>  用户不存在");
            throw new UsernameNotFoundException("用户不存在");
        }
        // TODO 可以加载该用户对应的权限
        return sysUser;
    }
}
