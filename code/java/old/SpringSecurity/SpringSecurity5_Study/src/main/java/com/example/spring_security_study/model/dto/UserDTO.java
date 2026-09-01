package com.example.spring_security_study.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/12 0012
 */
@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = -1764782435950874755L;

    /**
     * 用户账号
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;

}
