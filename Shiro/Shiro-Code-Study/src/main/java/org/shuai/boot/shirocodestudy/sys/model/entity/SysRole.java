package org.shuai.boot.shirocodestudy.sys.model.entity;

import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(value = "sys_role")
public class SysRole implements Serializable {

    private Long id;
    private String roleName;
    private String roleCode;
    private String description;
    private String status;
    private Date createTime;
    private Date updateTime;
}
