package org.shuai.boot.shirocodestudy.sys.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysUser implements Serializable {



    private Long id;
    private String username;
    private String password;
    private String status;
}
