package com.it.component;

import com.it.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @Title: 心动的offer->17k
 * @Author 帅宏编码-coding
 * @Date 2025/1/19 星期日 20:17
 */
@Component
public class UserController {
    public User getUser() {
        return new User("小帅", 18, "男");
    }
}

