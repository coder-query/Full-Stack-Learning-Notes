package org.shuai.boot.shirocodestudy.shiro.realm;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.shuai.boot.shirocodestudy.shiro.entity.SysUser;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    private static final String SALT = "123456@~realm~salt";

    private static final int HASH_ITERATIONS = 1024;

    public static final String TEST_PASSWORD = "123456";

    public static final String TEST_USERNAME = "admin";

    public static final String TEST_ROlE = "超级管理员";

    public static final String TEST_PERMISSION = "sys:user:add";

    public static void main(String[] args) {
        Sha256Hash Sha256Hash = new Sha256Hash(TEST_PASSWORD, SALT, HASH_ITERATIONS);
        String hex = Sha256Hash.toHex();
        System.out.println(hex);
    }

    private SysUser selectUserByUsername(String username) {
        if (StrUtil.equals(username, TEST_USERNAME)){
            return SysUser.builder()
                    .id(1L)
                    .username(TEST_USERNAME)
                    .password("5bbb772900403596ab58bab5312103cb537fccd0b59bec4d73cd3e8372d1ca8e")
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
        Sha256Hash Sha256Hash = new Sha256Hash(password, SALT, HASH_ITERATIONS);
        String hex = Sha256Hash.toHex();
        if (!StrUtil.equals(hex, sysUser.getPassword())){
            throw new IncorrectCredentialsException("用户名或密码错误,请重新输入");
        }
        return new SimpleAuthenticationInfo(sysUser, password,CustomRealm.class.getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        Set<String> roles = selectRolesByUserId(sysUser.getId());
        Set<String> permissions = selectPermissionsByRoleId(sysUser.getId());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }
}
