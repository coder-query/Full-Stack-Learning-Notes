package com.mdk.springsecurity6study.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mdk.springsecurity6study.common.entity.menu.MdkUmcMenu;
import com.mdk.springsecurity6study.common.entity.role.MdkUmcRole;
import com.mdk.springsecurity6study.common.entity.role.MdkUmcRoleMenuRela;
import com.mdk.springsecurity6study.common.entity.user.MdkUmcUser;
import com.mdk.springsecurity6study.common.entity.user.MdkUmcUserRole;
import com.mdk.springsecurity6study.mapper.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SysUserDetailsService implements UserDetailsService {
    /**
     * 获取用户信息
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    private final MdkUmcUserMapper mdkUmcUserMapper;
    private final MdkUmcRoleMapper mdkUmcRoleMapper;
    private final MdkUmcRoleMenuRelaMapper mdkUmcRoleMenuRelaMapper;
    private final MdkUmcMenuMapper mdkUmcMenuMapper;
    private final MdkUmcUserRoleMapper mdkUmcUserRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<MdkUmcUser> mdkUmcUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        mdkUmcUserLambdaQueryWrapper
                .eq(MdkUmcUser::getAccount, username)
                .eq(MdkUmcUser::getDelFlag, 0)
                .eq(MdkUmcUser::getStatus, 0);
        MdkUmcUser mdkUmcUser = mdkUmcUserMapper.selectOne(mdkUmcUserLambdaQueryWrapper);
        if (mdkUmcUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        /**
         *  获取角色标识符
         */
        // 先查询user1和role的关联表
        LambdaQueryWrapper<MdkUmcUserRole> mdkUmcRoleMapperLambdaQueryWrapper = new LambdaQueryWrapper<>();
        mdkUmcRoleMapperLambdaQueryWrapper
                .eq(MdkUmcUserRole::getUserId, mdkUmcUser.getId());
        List<MdkUmcUserRole> mdkUmcUserRoleList = mdkUmcUserRoleMapper.selectList(mdkUmcRoleMapperLambdaQueryWrapper);

        // stream 流 map 映射出 roleIdList
        List<Integer> roleIdList = mdkUmcUserRoleList.stream().map(MdkUmcUserRole::getRoleId).toList();

        // 查询role 表
        LambdaQueryWrapper<MdkUmcRole> mdkUmcRoleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        mdkUmcRoleLambdaQueryWrapper
                .in(MdkUmcRole::getId, roleIdList)
                .eq(MdkUmcRole::getDelFlag, 0)
                .eq(MdkUmcRole::getStatus, 0);
        List<MdkUmcRole> mdkUmcRoleList = mdkUmcRoleMapper.selectList(mdkUmcRoleLambdaQueryWrapper);

        // stream 流 map 映射出 roleKeySet
        Set<String> roleKeySet = mdkUmcRoleList.stream().map(MdkUmcRole::getRoleKey).collect(Collectors.toSet());
        mdkUmcUser.setRoleSet(roleKeySet);

        /**
         * 获取权限标识符
         */
        // 根据roleIdList 查询roleMenuRela 表
        LambdaQueryWrapper<MdkUmcRoleMenuRela> mdkUmcRoleMenuRelaLambdaQueryWrapper = new LambdaQueryWrapper<>();
        mdkUmcRoleMenuRelaLambdaQueryWrapper
                .in(MdkUmcRoleMenuRela::getRoleId, roleIdList)
                .eq(MdkUmcRoleMenuRela::getDelFlag, 0);
        List<MdkUmcRoleMenuRela> mdkUmcRoleMenuRelaList = mdkUmcRoleMenuRelaMapper.selectList(mdkUmcRoleMenuRelaLambdaQueryWrapper);

        // stream 流 map 映射出 menuIdList
        List<Integer> menuIdList = mdkUmcRoleMenuRelaList.stream().map(MdkUmcRoleMenuRela::getMenuId).toList();

        // 根据menuIdList 获取menu 表
        LambdaQueryWrapper<MdkUmcMenu> mdkUmcMenuLambdaQueryWrapper = new LambdaQueryWrapper<>();
        mdkUmcMenuLambdaQueryWrapper
                .in(MdkUmcMenu::getMenuId, menuIdList)
                .eq(MdkUmcMenu::getDelFlag, 0);
        List<MdkUmcMenu> mdkUmcMenuList = mdkUmcMenuMapper.selectList(mdkUmcMenuLambdaQueryWrapper);

        // stream 流 map 映射出 permsKeySet
        Set<String> permsKeySet = mdkUmcMenuList.stream().map(MdkUmcMenu::getPerms).collect(Collectors.toSet());
        mdkUmcUser.setPermissionSet(permsKeySet);

        // 返回用户信息
        return mdkUmcUser;
    }
}
