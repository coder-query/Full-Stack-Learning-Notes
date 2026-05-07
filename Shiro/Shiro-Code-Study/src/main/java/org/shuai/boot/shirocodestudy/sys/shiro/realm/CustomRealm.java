package org.shuai.boot.shirocodestudy.sys.shiro.realm;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.shuai.boot.shirocodestudy.sys.model.entity.SysUser;
import org.shuai.boot.shirocodestudy.sys.service.UserService;
import org.shuai.boot.shirocodestudy.sys.shiro.token.JwtToken;
import org.shuai.boot.shirocodestudy.sys.shiro.utils.JwtUtils;
import org.shuai.boot.shirocodestudy.sys.shiro.utils.ShiroHashUtils;
import org.shuai.boot.shirocodestudy.sys.shiro.utils.ShiroUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Component
public class CustomRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    public CustomRealm() {
        super();
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        // 支持JwtToken类型
        return token instanceof JwtToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        // JWT Token 认证，直接根据用户名构造认证信息
        if (authenticationToken instanceof JwtToken) {
            JwtToken jwtToken = (JwtToken) authenticationToken;
            String token = jwtToken.getPrincipal();
            if (StrUtil.isBlank(token)) {
                throw new AuthenticationException("token不存在");
            }
        // 提取clams
            Map<String, Object> claims = JwtUtils.extractClaims(token);
            SysUser sysUser = null;
            if (MapUtil.isNotEmpty(claims)){
                sysUser = (SysUser) claims.getOrDefault(JwtUtils.SYS_USER_INFO,null);
            }
            return new SimpleAuthenticationInfo(sysUser, jwtToken.getCredentials(), CustomRealm.class.getName());
        }
        return null;

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Subject subject = ShiroUtils.getSubject();
        if (Objects.isNull(subject) || !subject.isAuthenticated()){
            throw  new AuthenticationException("用户未认证，请先登录认证...");
        }
        log.info("doGetAuthorizationInfo 数据库查询权限");
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        Set<String> roles = userService.getRoleCodesByUserId(sysUser.getId());
        Set<String> permissions = userService.getPermissionsByUserId(sysUser.getId());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }
}
