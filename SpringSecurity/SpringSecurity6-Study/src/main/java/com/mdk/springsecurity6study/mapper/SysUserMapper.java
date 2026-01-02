package com.mdk.springsecurity6study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mdk.springsecurity6study.common.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 27986
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2026-01-02 19:33:49
* @Entity generator.domain.SysUser
*/
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}




