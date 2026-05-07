package org.shuai.boot.shirocodestudy.sys.service;

import org.shuai.boot.shirocodestudy.sys.model.entity.SysUser;

import java.util.Set;

public interface UserService {

    /**
     * 根据用户名查询用户
     */
    SysUser getUserByUsername(String username);

    /**
     * 根据用户ID查询角色编码集合
     */
    Set<String> getRoleCodesByUserId(Long userId);

    /**
     * 根据用户ID查询权限标识集合
     */
    Set<String> getPermissionsByUserId(Long userId);

    /**
     * 新增用户
     */
    boolean addUser(SysUser sysUser);

    /**
     * 根据ID删除用户
     */
    boolean deleteUserById(Long id);

    /**
     * 更新用户
     */
    boolean updateUser(SysUser sysUser);

    /**
     * 根据ID查询用户
     */
    SysUser getUserById(Long id);
}
