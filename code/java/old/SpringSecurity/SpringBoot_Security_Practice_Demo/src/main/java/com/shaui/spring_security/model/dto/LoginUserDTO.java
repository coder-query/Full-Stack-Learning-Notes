package com.shaui.spring_security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/13 0013
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDTO implements Serializable {
    private static final long serialVersionUID = -7544148197133561012L;

    private String username;

    private String password;

}
