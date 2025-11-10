package shuai.controller.test;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/send")
    public String send(HttpServletRequest httpServletRequest) {
//                    "DETAILS_USER_ID",   // 对应网关添加的用户ID头
//            "DETAILS_USERNAME",  // 对应网关添加的用户名头
//            "USER_KEY"
        String detailsUserIdValue = httpServletRequest.getHeader("DETAILS_USER_ID");
        String detailsUsernameValue = httpServletRequest.getHeader("DETAILS_USERNAME");
        String userKeyValue = httpServletRequest.getHeader("USER_KEY");
        System.out.println("DETAILS_USER_ID: " + detailsUserIdValue);
        System.out.println("DETAILS_USERNAME: " + detailsUsernameValue);
        System.out.println("USER_KEY: " + userKeyValue);
        return "send生产服务B 被调用了。。。" + detailsUserIdValue + " " + detailsUsernameValue + " " + userKeyValue;
    }

    @RequestMapping("/predicateTest")
    public String predicateTest(){
        return "predicateTest生产服务C - 7003 被调佣了。。。";
    }
}
