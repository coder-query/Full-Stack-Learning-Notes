package org.shuai.sys.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shuai.sys.mapper.RoleMapper;
import org.shuai.sys.model.entity.SysRole;
import org.shuai.sys.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.shuai.sys.model.entity.table.SysRoleTableDef.SYS_ROLE;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    @Override
    public SysRole getRoleByCode(String roleCode) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .where(SYS_ROLE.ROLE_CODE.eq(roleCode));
        return roleMapper.selectOneByQuery(queryWrapper);
    }

    @Override
    public List<SysRole> listRoles() {
        return roleMapper.selectListByQuery(QueryWrapper.create());
    }

    @Override
    public boolean addRole(SysRole sysRole) {
        return roleMapper.insert(sysRole) > 0;
    }

    @Override
    public boolean deleteRoleById(Long id) {
        return roleMapper.deleteById(id) > 0;
    }

    @Override
    public boolean updateRole(SysRole sysRole) {
        return roleMapper.update(sysRole) > 0;
    }

    @Override
    public SysRole getRoleById(Long id) {
        return roleMapper.selectOneById(id);
    }
}
