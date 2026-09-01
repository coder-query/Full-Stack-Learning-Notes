package org.shuai;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootTest
class TestJdbcRealm {

    @Resource
    private DataSource dataSource;

    @Test
    void testSimpleAccountRealm() {
        //认证的发起者(subject), SecurityManager, Realm
        //1. 准备Realm (基于内存存储用户信息)
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);

        //2. 准备SecurityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //3. SecurityManager和Realm建立连接
        securityManager.setRealm(jdbcRealm);

        //4. subject和SecurityManager建立联系
        SecurityUtils.setSecurityManager(securityManager);

        //5. 声明subject
        Subject subject = SecurityUtils.getSubject();

        //6. 发起认证
        subject.login(new UsernamePasswordToken("admin", "admin"));
        // 如果认证时，用户名错误，抛出: org.apache.shiro.authc.UnknownAccountException异常
        // 如果认证时，密码错误，抛出: org.apache.shiro.authc.IncorrectCredentialsException:

        //7. 判断是否认证成功
        System.out.println(subject.isAuthenticated());

        //8. 授权是在认证成功之后的操作！！！
        // SimpleAccountRealm只支持角色的授权
        System.out.println("是否拥有超级管理员角色：" + subject.hasRole("超级管理员"));
        subject.checkRole("商家1");
        // check方法校验角色时，如果没有指定角色，会抛出异常: org.apache.shiro.authz.UnauthorizedException: Subject does not have role [商家1]

        //9. 退出登录后再判断
        subject.logout();
        System.out.println("logout方法执行后，认证的状态：" + subject.isAuthenticated());


    }

}
