package com.shuai.springboot3_http_client.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RemoteLoginRequestDTO {
    @Schema(description = "账号")
    @JsonProperty("loginAccount")
    private String loginAccount;

    @Schema(description = "密码")
    @JsonProperty("loginPassword")
    private String loginPassword;
}