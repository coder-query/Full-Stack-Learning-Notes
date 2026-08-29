package com.mdk.springsecurity6study.common.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "用户登录参数")
public class LoginDTO {

    @Schema(description = "账号")
    private String username;

    @Schema(description = "密码")
    private String password;
}
