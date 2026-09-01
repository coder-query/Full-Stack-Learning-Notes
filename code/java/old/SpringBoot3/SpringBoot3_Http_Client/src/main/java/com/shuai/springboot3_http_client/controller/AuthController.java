package com.shuai.springboot3_http_client.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.shuai.springboot3_http_client.common.dto.LoginDTO;
import com.shuai.springboot3_http_client.common.response.Result;
import com.shuai.springboot3_http_client.common.dto.RemoteLoginRequestDTO;
import com.shuai.springboot3_http_client.holder.RemoteTokenContextHolder;
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

    @Resource
    private RemoteTokenContextHolder remoteTokenContextHolder;

    @PostMapping("/login")
    public Result<Object> login(@RequestBody LoginDTO loginDTO) {
        log.info("loginDTO: {}", loginDTO);
        String loginAccount = loginDTO.getLoginAccount();
        String loginPassword = loginDTO.getLoginPassword();
        RemoteLoginRequestDTO remoteLoginRequestDTO = new RemoteLoginRequestDTO(loginAccount.trim(), loginPassword.trim());
        
        String res = umsAuthHttpApi.login(remoteLoginRequestDTO);
        log.info("远程登录响应: {}", res);
        
        // 解析响应并存储token到session
        JSONObject jsonObject = JSON.parseObject(res);
        // 根据实际响应结构获取token，这里假设token在data.token或直接在token字段
        String token = extractToken(jsonObject);
        if (token != null) {
            remoteTokenContextHolder.putToken(token);
            log.info("登录成功，token已存入session");
        }
        
        return Result.success(jsonObject);
    }

    /**
     * 从响应中提取token，根据实际响应结构调整
     */
    private String extractToken(JSONObject jsonObject) {
        // 尝试常见的token字段路径
        if (jsonObject.containsKey("token")) {
            return jsonObject.getString("token");
        }
        if (jsonObject.containsKey("data")) {
            JSONObject data = jsonObject.getJSONObject("data");
            if (data != null && data.containsKey("token")) {
                return data.getString("token");
            }
            if (data != null && data.containsKey("accessToken")) {
                return data.getString("accessToken");
            }
        }
        if (jsonObject.containsKey("accessToken")) {
            return jsonObject.getString("accessToken");
        }
        return null;
    }
}
