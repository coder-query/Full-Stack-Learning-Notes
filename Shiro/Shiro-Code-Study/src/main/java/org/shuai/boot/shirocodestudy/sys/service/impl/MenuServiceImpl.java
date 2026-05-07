package org.shuai.boot.shirocodestudy.sys.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shuai.boot.shirocodestudy.sys.mapper.MenuMapper;
import org.shuai.boot.shirocodestudy.sys.model.entity.SysMenu;
import org.shuai.boot.shirocodestudy.sys.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;

    @Override
    public List<SysMenu> listMenus() {
        return menuMapper.selectListByQuery(QueryWrapper.create());
    }

    @Override
    public boolean addMenu(SysMenu sysMenu) {
        return menuMapper.insert(sysMenu) > 0;
    }

    @Override
    public boolean deleteMenuById(Long id) {
        return menuMapper.deleteById(id) > 0;
    }

    @Override
    public boolean updateMenu(SysMenu sysMenu) {
        return menuMapper.update(sysMenu) > 0;
    }

    @Override
    public SysMenu getMenuById(Long id) {
        return menuMapper.selectOneById(id);
    }
}
