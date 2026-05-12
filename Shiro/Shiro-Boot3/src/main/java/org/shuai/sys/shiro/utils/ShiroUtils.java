package org.shuai.sys.shiro.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.shuai.sys.model.entity.SysUser;

public class ShiroUtils {

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static SysUser getUser() {
        Object object = ShiroUtils.getSubject().getPrincipal();
        return (SysUser) object;
    }

    public static void login(String username, String password) {
        ShiroUtils.getSubject().login(new UsernamePasswordToken(username, password));
    }

    public static void logout() {
        ShiroUtils.getSubject().logout();
    }

}
