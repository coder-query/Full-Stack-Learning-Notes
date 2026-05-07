package org.shuai.boot.shirocodestudy.sys.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;
import org.shuai.boot.shirocodestudy.sys.model.entity.SysUser;

/**
 * JWT Token，用于Shiro认证
 */
public class JwtToken implements AuthenticationToken {

    private final String token;

    public JwtToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public String getCredentials() {
        return token;
    }
}
