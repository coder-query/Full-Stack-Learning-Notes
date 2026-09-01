package com.shaui.spring_security.config.filter;

import com.shaui.spring_security.common.StringCommons;
import com.shaui.spring_security.model.entity.LoginUserDetails;
import com.shaui.spring_security.utils.JwtUtils;
import com.shaui.spring_security.utils.RedisCacheUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private RedisCacheUtils redisCacheUtils;
    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 从请求头中取出jwt令牌
        String token = request.getHeader(tokenHeader);
        if (!StringUtils.hasText(token)) {
            // token值为空,放行,让后面的权限过滤器进行拦截
            filterChain.doFilter(request, response);
            return;
        }

        // 解析token 获取 userId
        String userId = jwtUtils.getUserId(token).toString();

        // 从Redis中获取用户信息
        // 正确获取用户信息
        Object userObj = redisCacheUtils.getCacheObject(StringCommons.REDIS_LOGIN_KEY + userId);
        LoginUserDetails loginUserDetails = null;
        if (userObj instanceof LoginUserDetails) {
            loginUserDetails = (LoginUserDetails) userObj;
            // 使用获取到的用户信息
        } else if (userObj != null) {
            // 处理可能的类型转换问题
            throw new RuntimeException("Redis中存储的用户信息格式不正确");
        } else {
            throw new RuntimeException("用户未登录");
        }
        // 存入SecurityContext 上下文中
        //TODO 获取用户的权限信息封装到 Authentication对象中
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUserDetails, null, loginUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}
