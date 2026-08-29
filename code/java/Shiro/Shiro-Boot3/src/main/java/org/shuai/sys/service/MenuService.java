package org.shuai.sys.service;

import org.shuai.sys.model.entity.SysMenu;

import java.util.List;

public interface MenuService {

    /**
     * 查询所有菜单
     */
    List<SysMenu> listMenus();

    /**
     * 新增菜单
     */
    boolean addMenu(SysMenu sysMenu);

    /**
     * 根据ID删除菜单
     */
    boolean deleteMenuById(Long id);

    /**
     * 更新菜单
     */
    boolean updateMenu(SysMenu sysMenu);

    /**
     * 根据ID查询菜单
     */
    SysMenu getMenuById(Long id);
}
