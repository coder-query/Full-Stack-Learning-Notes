package org.shuai.boot.shirocodestudy.sys.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * JWT Token，用于Shiro认证
 */
public class JwtToken implements AuthenticationToken {

    private final String token;
    private final String username;

    public JwtToken(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
