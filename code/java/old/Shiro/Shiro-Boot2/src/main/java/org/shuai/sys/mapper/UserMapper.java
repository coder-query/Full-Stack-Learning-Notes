package org.shuai.sys.mapper;

import com.mybatisflex.core.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.shuai.sys.model.entity.SysUser;

import java.util.Set;

public interface UserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户ID查询角色编码集合
     */
    Set<String> selectRoleCodesByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID查询权限标识集合
     */
    Set<String> selectPermissionsByUserId(@Param("userId") Long userId);
}
