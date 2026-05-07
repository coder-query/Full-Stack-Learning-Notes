package org.shuai.boot.shirocodestudy.sys.shiro.filter;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.shuai.boot.shirocodestudy.sys.shiro.token.JwtToken;
import org.shuai.boot.shirocodestudy.sys.shiro.utils.JwtUtils;
import org.shuai.boot.shirocodestudy.sys.shiro.utils.ShiroUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class JwtAuthFilter extends AccessControlFilter {

    public static final String JWT_TOKEN_HEADER = "X-Access-JWT-Token";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        log.info("isAccessAllowed");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwtToken = httpServletRequest.getHeader(JWT_TOKEN_HEADER);
        if (StringUtils.isBlank(jwtToken)) {
            log.error("jwtToken is empty");
            throw new AuthenticationException("token is empty");
        }
        // 调用Shiro的登录逻辑，进入自定义的realm
        Subject subject = SecurityUtils.getSubject();
        if (Objects.isNull(subject)){
            throw new AuthenticationException("subject is null");
        }
       try {
           subject.login(new JwtToken(jwtToken));
       }catch (AuthenticationException e){
           log.error("JwtAuthFilter 验证出报错, e = ",e);
           throw e;
       }
       // 认证通过，返回true，放行
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
