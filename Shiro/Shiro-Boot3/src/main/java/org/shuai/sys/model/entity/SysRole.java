package org.shuai.sys.model.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
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

    @Id(keyType = KeyType.Auto)
    private Long id;

    private String roleName;

    private String roleCode;

    private String description;

    private String status;

    @Column(onInsertValue = "now()")
    private Date createTime;

    @Column(onInsertValue = "now()", onUpdateValue = "now()")
    private Date updateTime;
}
