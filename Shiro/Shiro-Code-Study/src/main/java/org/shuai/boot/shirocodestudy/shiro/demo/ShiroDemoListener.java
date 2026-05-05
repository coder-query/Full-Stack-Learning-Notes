package org.shuai.boot.shirocodestudy.shiro.demo;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.shuai.boot.shirocodestudy.shiro.realm.CustomRealm;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class ShiroDemoListener implements ApplicationListener<ApplicationReadyEvent> {

    @Resource
    private SecurityManager securityManager;

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        log.warn("ShiroDemoListener onApplicationEvent, 开始测试校验密码。。。");
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken( CustomRealm.TEST_USERNAME, CustomRealm.TEST_PASSWORD));

        System.out.println("是否拥有管理员角色 ："+subject.hasRole(CustomRealm.TEST_ROlE));

        System.out.println("是否拥有权限："+subject.isPermitted(CustomRealm.TEST_PERMISSION));
    }
}
