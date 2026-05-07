package org.shuai.boot.shirocodestudy.sys.shiro.filter;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.shuai.boot.shirocodestudy.response.Response;
import org.shuai.boot.shirocodestudy.sys.shiro.token.JwtToken;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
public class JwtAuthFilter extends AccessControlFilter {

    public static final String JWT_TOKEN_HEADER = "X-Access-JWT-Token";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        log.info("JwtAuthFilter ----> isAccessAllowed");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwtToken = httpServletRequest.getHeader(JWT_TOKEN_HEADER);
        if (StringUtils.isBlank(jwtToken)) {
            log.warn("jwtToken is empty");
            return false;
        }
        // 调用Shiro的登录逻辑，进入自定义的realm
        Subject subject = SecurityUtils.getSubject();
        if (Objects.isNull(subject)){
            return false;
        }
       try {
           subject.login(new JwtToken(jwtToken));
       }catch (AuthenticationException e){
           log.error("JwtAuthFilter 验证出报错, e = ",e);
           return false;
       }
       // 认证通过，返回true，放行
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        log.info("JwtAuthFilter ----> onAccessDenied");
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setContentType("application/json;charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        resp.getWriter().write(JSON.toJSONString(Response.unauthorized("未登录或Token已过期")));
        return false;
    }
}
