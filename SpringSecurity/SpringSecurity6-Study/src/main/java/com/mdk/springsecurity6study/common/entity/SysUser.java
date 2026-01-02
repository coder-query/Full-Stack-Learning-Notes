package com.mdk.springsecurity6study.common.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_user")
public class SysUser extends BaseEntity implements Serializable, UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 真实姓名
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 最后登录时间
     */
    @TableField(value = "last_login_at")
    private LocalDateTime lastLoginAt;

    /**
     * 最后登录IP
     */
    @TableField(value = "last_login_ip")
    private String lastLoginIp;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 删除时间
     */
    @TableField(value = "deleted_at")
    private LocalDateTime deletedAt;

    /**
     * 逻辑删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
