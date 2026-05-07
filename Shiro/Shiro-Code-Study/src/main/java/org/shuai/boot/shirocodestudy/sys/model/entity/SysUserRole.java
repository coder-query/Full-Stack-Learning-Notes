package org.shuai.boot.shirocodestudy.sys.model.entity;

import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(value = "sys_user_role")
public class SysUserRole implements Serializable {

    private Long id;
    private Long userId;
    private Long roleId;
}
