package org.shuai.sys.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shuai.sys.mapper.RoleMenuMapper;
import org.shuai.sys.model.entity.SysRoleMenu;
import org.shuai.sys.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.shuai.sys.model.entity.table.SysRoleMenuTableDef.SYS_ROLE_MENU;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleMenuServiceImpl implements RoleMenuService {

    private final RoleMenuMapper roleMenuMapper;

    @Override
    public List<SysRoleMenu> listByRoleId(Long roleId) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(SYS_ROLE_MENU.ROLE_ID.eq(roleId));
        return roleMenuMapper.selectListByQuery(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean assignMenusToRole(Long roleId, List<Long> menuIds) {
        removeByRoleId(roleId);
        if (menuIds != null && !menuIds.isEmpty()) {
            for (Long menuId : menuIds) {
                SysRoleMenu roleMenu = SysRoleMenu.builder()
                        .roleId(roleId)
                        .menuId(menuId)
                        .build();
                roleMenuMapper.insert(roleMenu);
            }
        }
        return true;
    }

    @Override
    public boolean removeByRoleId(Long roleId) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(SYS_ROLE_MENU.ROLE_ID.eq(roleId));
        return roleMenuMapper.deleteByQuery(queryWrapper) >= 0;
    }
}
