package org.shuai.boot.shirocodestudy.listener;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.shuai.boot.shirocodestudy.shiro.realm.CustomRealm;
import org.shuai.boot.shirocodestudy.shiro.utils.ShiroUtils;
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
        // 在非 HTTP 请求线程中，需要手动绑定 SecurityManager 到 ThreadContext
        ThreadContext.bind(securityManager);
        try {
            ShiroUtils.login(CustomRealm.TEST_USERNAME, CustomRealm.TEST_PASSWORD);
            Subject subject = ShiroUtils.getSubject();
            log.info("登录成功, principal = {}", subject.getPrincipal());
            subject.logout();
        } catch (Exception e) {
            log.error("登录测试失败", e);
        } finally {
            ThreadContext.remove();
        }
    }
}
