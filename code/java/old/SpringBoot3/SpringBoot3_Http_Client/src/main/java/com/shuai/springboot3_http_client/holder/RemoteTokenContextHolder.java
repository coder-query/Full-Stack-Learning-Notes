package com.shuai.springboot3_http_client.holder;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Slf4j
public class RemoteTokenContextHolder {

    private static final String TOKEN_KEY = "remote_token";

    /**
     * 存入token到服务器的本地session
     */
    public void putToken(String token) {
        HttpSession session = getSession();
        if (session != null) {
            session.setAttribute(TOKEN_KEY, token);
            log.info("存入token: {}", token);
        }
    }

    /**
     * 从session中获取token
     */
    public String getToken() {
        HttpSession session = getSession();
        if (session != null) {
            Object token = session.getAttribute(TOKEN_KEY);
            log.info("取出token: {}", token);
            return token != null ? token.toString() : null;
        }
        return null;
    }

    /**
     * 清除token
     */
    public void clearToken() {
        HttpSession session = getSession();
        if (session != null) {
            session.removeAttribute(TOKEN_KEY);
            log.info("清除token");
        }
    }

    private HttpSession getSession() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return attributes.getRequest().getSession();
        }
        return null;
    }
}
