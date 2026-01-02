package com.mdk.springsecurity6study.service.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdk.springsecurity6study.common.entity.SysUser;

/**
* @author 27986
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2026-01-02 19:33:49
*/
public interface SysUserService extends IService<SysUser> {

    String login (String username, String password);
}
