package com.shuai.springboot3_http_client.controller;

import com.alibaba.fastjson2.JSON;
import com.shuai.springboot3_http_client.common.dto.LoginDTO;
import com.shuai.springboot3_http_client.common.response.Result;
import com.shuai.springboot3_http_client.common.dto.RemoteLoginRequestDTO;
import com.shuai.springboot3_http_client.service.http.UmsAuthHttpApi;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private UmsAuthHttpApi umsAuthHttpApi;
    @PostMapping("/login")
    public Result<Object> login(@RequestBody LoginDTO loginDTO) {
        log.info("loginDTO:{}",loginDTO);
        String loginAccount = loginDTO.getLoginAccount();
        String loginPassword = loginDTO.getLoginPassword();
        RemoteLoginRequestDTO remoteLoginRequestDTO = new RemoteLoginRequestDTO(loginAccount.trim(), loginPassword.trim());
        String res = umsAuthHttpApi.login(remoteLoginRequestDTO);
        log.info("res:{}",res);
        return Result.success(JSON.parseObject(res));
    }
}
