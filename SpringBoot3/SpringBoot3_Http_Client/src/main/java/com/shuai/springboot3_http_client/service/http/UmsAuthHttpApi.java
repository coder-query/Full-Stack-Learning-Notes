package com.shuai.springboot3_http_client.service.http;

import com.shuai.springboot3_http_client.common.dto.RemoteLoginRequestDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * 定义Http接口，用于调用远程的Auth服务
 */
@HttpExchange
public interface UmsAuthHttpApi {

    @PostExchange(value = "/auth/login", contentType = MediaType.APPLICATION_JSON_VALUE, accept = MediaType.APPLICATION_JSON_VALUE)
    String login(@RequestBody RemoteLoginRequestDTO remoteLoginRequestDTO);
}