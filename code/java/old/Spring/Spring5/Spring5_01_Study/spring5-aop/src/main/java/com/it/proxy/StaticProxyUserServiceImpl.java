package com.it.proxy;


import com.it.pojo.User;
import com.it.service.IUserService;
import lombok.Data;
import org.springframework.stereotype.Component;

// 定义一个静态代理类，用于为 UserServiceImpl 提供代理服务
@Component
@Data
public class StaticProxyUserServiceImpl implements IUserService {

    // 目标对象，即被代理的对象
    private IUserService target;

    // 构造方法，初始化目标对象
    public StaticProxyUserServiceImpl(IUserService spc ) {
        this.target = spc;
    }

    @Override
    public User getUser() {
        System.out.println("静态代理开始执行...UserServiceImpl被代理");
        User user = target.getUser();
        System.out.println("静态代理结束...UserServiceImpl被代理");
        return user;
    }
}
