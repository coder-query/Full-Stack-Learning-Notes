package org.shuai.boot.shirocodestudy.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysUser {
    private Long id;
    private String username;
    private String password;
    private String status;
}
