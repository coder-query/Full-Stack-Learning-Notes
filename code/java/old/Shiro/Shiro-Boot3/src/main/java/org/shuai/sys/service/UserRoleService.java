package org.shuai.sys.service;

import org.shuai.sys.model.entity.SysUserRole;

import java.util.List;

public interface UserRoleService {

    /**
     * 根据用户ID查询关联关系
     */
    List<SysUserRole> listByUserId(Long userId);

    /**
     * 根据角色ID查询关联关系
     */
    List<SysUserRole> listByRoleId(Long roleId);

    /**
     * 为用户分配角色
     */
    boolean assignRolesToUser(Long userId, List<Long> roleIds);

    /**
     * 删除用户的角色关联
     */
    boolean removeByUserId(Long userId);
}
