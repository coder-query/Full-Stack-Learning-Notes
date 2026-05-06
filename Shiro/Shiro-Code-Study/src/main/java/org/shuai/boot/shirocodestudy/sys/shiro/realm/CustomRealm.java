package org.shuai.boot.shirocodestudy.sys.shiro.realm;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.shuai.boot.shirocodestudy.sys.model.entity.SysUser;
import org.shuai.boot.shirocodestudy.sys.shiro.token.JwtToken;
import org.shuai.boot.shirocodestudy.sys.shiro.utils.ShiroPwdUtils;
import org.shuai.boot.shirocodestudy.sys.shiro.utils.ShiroUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@Slf4j
@Component
public class CustomRealm extends AuthorizingRealm {

    public static final String TEST_PASSWORD = "123456";

    public static final String TEST_USERNAME = "admin";

    public static final String TEST_ROlE = "超级管理员";

    public static final String TEST_PERMISSION = "sys:user:add";

    public CustomRealm() {
        super();
        // 支持JwtToken和UsernamePasswordToken两种类型
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken || token instanceof JwtToken;
    }

    public static void main(String[] args) {
        String hex = ShiroPwdUtils.encryptWithSha256(TEST_PASSWORD);
        System.out.println(hex);
    }

    private SysUser selectUserByUsername(String username) {
        if (StrUtil.equals(username, TEST_USERNAME)){
            return SysUser.builder()
                    .id(1L)
                    .username(TEST_USERNAME)
                    .password("fb6d6a86368e7dc6973a976a055a91194180b2b7e000207cd88d87158dc831c4")
                    .status("1")
                    .build();
        }
        return null;
    }

    public Set<String> selectRolesByUserId(Long Id) {
        return CollUtil.newHashSet(TEST_ROlE, "商家");
    }
    public Set<String> selectPermissionsByRoleId(Long roleId) {
        return CollUtil.newHashSet(TEST_PERMISSION, "sys:user:delete","sys:user:update","sys:user:select");
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        // JWT Token 认证：JWT已在Filter中验证通过，直接根据用户名构造认证信息
        if (authenticationToken instanceof JwtToken) {
            JwtToken jwtToken = (JwtToken) authenticationToken;
            String username = (String) jwtToken.getPrincipal();
            SysUser sysUser = selectUserByUsername(username);
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

        // 模拟查询数据库
        SysUser sysUser = selectUserByUsername(username);

        if (Objects.isNull(sysUser)){
            throw new UnknownAccountException("用户名或密码错误,请重新输入");
        }

        // 校验密码
        if (!ShiroPwdUtils.verifyWithSha256(password, sysUser.getPassword())){
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
        System.out.println("doGetAuthorizationInfo 数据库查询权限");
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        Set<String> roles = selectRolesByUserId(sysUser.getId());
        Set<String> permissions = selectPermissionsByRoleId(sysUser.getId());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }
}
