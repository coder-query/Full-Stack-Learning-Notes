package org.shuai.sys.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shuai.sys.mapper.UserRoleMapper;
import org.shuai.sys.model.entity.SysUserRole;
import org.shuai.sys.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.shuai.sys.model.entity.table.SysUserRoleTableDef.SYS_USER_ROLE;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleMapper userRoleMapper;

    @Override
    public List<SysUserRole> listByUserId(Long userId) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(SYS_USER_ROLE.USER_ID.eq(userId));
        return userRoleMapper.selectListByQuery(queryWrapper);
    }

    @Override
    public List<SysUserRole> listByRoleId(Long roleId) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(SYS_USER_ROLE.ROLE_ID.eq(roleId));
        return userRoleMapper.selectListByQuery(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean assignRolesToUser(Long userId, List<Long> roleIds) {
        removeByUserId(userId);
        if (roleIds != null && !roleIds.isEmpty()) {
            for (Long roleId : roleIds) {
                SysUserRole userRole = SysUserRole.builder()
                        .userId(userId)
                        .roleId(roleId)
                        .build();
                userRoleMapper.insert(userRole);
            }
        }
        return true;
    }

    @Override
    public boolean removeByUserId(Long userId) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(SYS_USER_ROLE.USER_ID.eq(userId));
        return userRoleMapper.deleteByQuery(queryWrapper) >= 0;
    }
}
