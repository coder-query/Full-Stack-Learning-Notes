package com;

import com.it.proxy.StaticAssistantProxy;
import com.it.service.Boss;
import com.it.service.Impl.BossImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/12 星期六
 */
public class Spring_AOP_Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext IOC =
                new ClassPathXmlApplicationContext("applicationContext.xml");
//
        Boss boss = IOC.getBean(BossImpl.class); // 拿的到吗? ---> 拿到的是代理对象
//
//        Boss boss = IOC.getBean(Boss.class);
//
//        // 如果这个bean没有被Spring AOP 代理, 那么这个类的对象直接放入IOC
//
//        // 如果这个bean被Spring AOP 代理, 那么放入IOC容器的是代理对象
//
//
        boss.startMeeting();

//        BossImpl bossImpl = new BossImpl();
//        // 静态代理 （写死代码）
//        // 代理类对象
//        StaticAssistantProxy staticAssistantProxy = new StaticAssistantProxy(bossImpl);
//        staticAssistantProxy.startMeeting();

        // 动态代理 （jdk）

        // 动态代理 （cglib）



    }
}
