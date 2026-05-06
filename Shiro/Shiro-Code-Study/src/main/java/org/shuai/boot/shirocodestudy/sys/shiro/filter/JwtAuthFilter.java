package org.shuai.boot.shirocodestudy.sys.shiro.filter;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.shuai.boot.shirocodestudy.sys.shiro.token.JwtToken;
import org.shuai.boot.shirocodestudy.sys.shiro.utils.JwtUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public class JwtAuthFilter extends AccessControlFilter {

    public static final String JWT_TOKEN_HEADER = "X-Access-JWT-Token";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        log.info("isAccessAllowed");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwtToken = httpServletRequest.getHeader(JWT_TOKEN_HEADER);
        if (StringUtils.isBlank(jwtToken)) {
            log.info("jwtToken is empty");
            return false;
        }
        // 验证JWT并提取用户信息
        Map<String, Object> claims = JwtUtils.verifyAndGetClaims(jwtToken);
        if (claims == null) {
            log.info("jwtToken is invalid or expired");
            return false;
        }
        // 用JWT中的用户名执行Shiro登录，让Subject变为已认证状态
        String username = (String) claims.get("username");
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            subject.login(new JwtToken(jwtToken, username));
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        log.info("onAccessDenied");
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setContentType("application/json;charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        resp.getWriter().write(JSON.toJSONString("无效用户或已过期"));
        return false;
    }
}
