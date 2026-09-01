package com.it.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hello")
public class HelloController {

    //真实访问地址 : 项目名/HelloController/hello
    @PostMapping("/h1")
    public String sayHello(@RequestParam("name") String name, Model model){
        //封装数据
        //向模型中添加属性msg与值，可以在JSP页面中取出并渲染
        model.addAttribute("msg",name);
        return "hello";   //web-inf/jsp/hello.jsp  会被视图解析器处理：
    }
}

