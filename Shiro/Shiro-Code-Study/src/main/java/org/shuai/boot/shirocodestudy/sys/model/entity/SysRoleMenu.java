package org.shuai.boot.shirocodestudy.sys.model.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
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
@Table(value = "sys_role_menu")
public class SysRoleMenu implements Serializable {

    @Id(keyType = KeyType.Auto)
    private Long id;

    private Long roleId;

    private Long menuId;
}
