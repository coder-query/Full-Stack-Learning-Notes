package org.shuai.boot.shirocodestudy.sys.shiro.realm;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.shuai.boot.shirocodestudy.sys.shiro.entity.SysUser;
import org.shuai.boot.shirocodestudy.sys.shiro.utils.ShiroPwdUtils;
import org.shuai.boot.shirocodestudy.sys.shiro.utils.ShiroUtils;

import java.util.Objects;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    public static final String TEST_PASSWORD = "123456";

    public static final String TEST_USERNAME = "admin";

    public static final String TEST_ROlE = "超级管理员";

    public static final String TEST_PERMISSION = "sys:user:add";

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
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        Set<String> roles = selectRolesByUserId(sysUser.getId());
        Set<String> permissions = selectPermissionsByRoleId(sysUser.getId());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }
}
