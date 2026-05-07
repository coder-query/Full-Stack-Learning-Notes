package org.shuai.boot.shirocodestudy.sys.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shuai.boot.shirocodestudy.sys.mapper.UserMapper;
import org.shuai.boot.shirocodestudy.sys.model.entity.SysUser;
import org.shuai.boot.shirocodestudy.sys.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Set;

import static org.shuai.boot.shirocodestudy.sys.model.entity.table.SysUserTableDef.SYS_USER;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public SysUser getUserByUsername(String username) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(SYS_USER.USERNAME.eq(username));
        return userMapper.selectOneByQuery(queryWrapper);
    }

    @Override
    public Set<String> getRoleCodesByUserId(Long userId) {
        return userMapper.selectRoleCodesByUserId(userId);
    }

    @Override
    public Set<String> getPermissionsByUserId(Long userId) {
        return userMapper.selectPermissionsByUserId(userId);
    }

    @Override
    public boolean addUser(SysUser sysUser) {
        return userMapper.insert(sysUser) > 0;
    }

    @Override
    public boolean deleteUserById(Long id) {
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public boolean updateUser(SysUser sysUser) {
        return userMapper.update(sysUser) > 0;
    }

    @Override
    public SysUser getUserById(Long id) {
        return userMapper.selectOneById(id);
    }
}
