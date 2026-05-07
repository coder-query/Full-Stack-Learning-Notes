package org.shuai.boot.shirocodestudy.sys.shiro.realm;

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
import org.shuai.boot.shirocodestudy.sys.shiro.utils.ShiroHashUtils;
import org.shuai.boot.shirocodestudy.sys.shiro.utils.ShiroUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomRealm extends AuthorizingRealm {

    private final UserService userService;

    public CustomRealm() {
        super();
        // 支持JwtToken和UsernamePasswordToken两种类型
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken || token instanceof JwtToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        // JWT Token 认证：JWT已在Filter中验证通过，直接根据用户名构造认证信息
        if (authenticationToken instanceof JwtToken) {
            JwtToken jwtToken = (JwtToken) authenticationToken;
            String username = (String) jwtToken.getPrincipal();
            SysUser sysUser = userService.getUserByUsername(username);
            if (Objects.isNull(sysUser)) {
                throw new UnknownAccountException("用户不存在");
            }
            return new SimpleAuthenticationInfo(sysUser, jwtToken.getCredentials(), CustomRealm.class.getName());
        }

        // 用户名密码认证
        String username = (String) authenticationToken.getPrincipal();

        char[] passwordChars = (char[]) authenticationToken.getCredentials();
        String password = new String(passwordChars);

        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            throw new UnknownAccountException("用户名或密码不能为空");
        }

        // 查询数据库
        SysUser sysUser = userService.getUserByUsername(username);

        if (Objects.isNull(sysUser)){
            throw new UnknownAccountException("用户名或密码错误,请重新输入");
        }

        // 校验密码
        if (!ShiroHashUtils.verifyWithSha256(password, sysUser.getPassword())){
            throw new IncorrectCredentialsException("用户名或密码错误,请重新输入");
        }
        return new SimpleAuthenticationInfo(sysUser, password,CustomRealm.class.getName());
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
