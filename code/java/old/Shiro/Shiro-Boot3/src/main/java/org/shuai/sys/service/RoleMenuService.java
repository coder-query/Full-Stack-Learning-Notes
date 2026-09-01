package org.shuai.sys.service;

import org.shuai.sys.model.entity.SysRoleMenu;

import java.util.List;

public interface RoleMenuService {

    /**
     * 根据角色ID查询关联关系
     */
    List<SysRoleMenu> listByRoleId(Long roleId);

    /**
     * 为角色分配菜单权限
     */
    boolean assignMenusToRole(Long roleId, List<Long> menuIds);

    /**
     * 删除角色的菜单关联
     */
    boolean removeByRoleId(Long roleId);
}
