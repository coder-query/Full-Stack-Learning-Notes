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
@Table(value = "sys_menu")
public class SysMenu implements Serializable {

    private Long id;
    private Long parentId;
    private String menuName;
    private String menuType;
    private String permission;
    private String path;
    private String icon;
    private Integer sortOrder;
    private String status;
    private Date createTime;
    private Date updateTime;
}
