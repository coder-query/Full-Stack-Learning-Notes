package org.shuai.sys.service;

import org.shuai.sys.model.entity.SysRole;

import java.util.List;

public interface RoleService {

    /**
     * 根据角色编码查询角色
     */
    SysRole getRoleByCode(String roleCode);

    /**
     * 查询所有角色
     */
    List<SysRole> listRoles();

    /**
     * 新增角色
     */
    boolean addRole(SysRole sysRole);

    /**
     * 根据ID删除角色
     */
    boolean deleteRoleById(Long id);

    /**
     * 更新角色
     */
    boolean updateRole(SysRole sysRole);

    /**
     * 根据ID查询角色
     */
    SysRole getRoleById(Long id);
}
