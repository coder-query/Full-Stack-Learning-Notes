package com.shuai.bootmapstruct.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 类功能：
 *
 * @author shuaihong-coding
 * @date 2026-01-12 18:01
 */
@Data
@Accessors(chain = true)
public class UserEntity {

    private String id;

    private Integer age;

    private String username;

    private String password;
}
