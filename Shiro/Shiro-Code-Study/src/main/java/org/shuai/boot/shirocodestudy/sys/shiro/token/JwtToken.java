package org.shuai.boot.shirocodestudy.sys.shiro.token;

import lombok.Getter;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * JWT Token，用于Shiro认证
 */
@Getter
public class JwtToken implements AuthenticationToken {

    private final String token;

    public JwtToken(String token) {
        this.token = token;
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
