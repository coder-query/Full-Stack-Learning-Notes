package com.shuai.springboot3_http_client.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RemoteLoginRequestDTO {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}