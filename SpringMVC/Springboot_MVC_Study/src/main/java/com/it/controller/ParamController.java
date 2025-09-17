package com.it.controller;

import com.it.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("param")
public class ParamController {

    /**
     * 前端请求: http://localhost:8085/param/value?name=xx&age=18
     * <p>
     * 可以利用形参列表,直接接收前端传递的param参数!
     * 要求: 参数名 = 形参名
     * 类型相同
     * 出现乱码正常，json接收具体解决！！
     *
     * @return 返回前端数据
     */
    @GetMapping(value = "/value")
    @ResponseBody
    public String setupForm(String name, Integer age) {
        System.out.println("name = " + name + ", age = " + age);
        return name + " " + age;
    }


    /**
     * 前端请求: http://localhost:8085/param/data?name=xx&stuAge=18
     * <p>
     * 使用@RequestParam注解标记handler方法的形参
     * 指定形参对应的请求参数@RequestParam(请求参数名称)
     */
    @GetMapping(value = "/data")
    @ResponseBody
    public Object paramForm(@RequestParam("name") String name,
                            @RequestParam(value = "stuAge", required = false, defaultValue = "18") Integer age) {
        System.out.println("name = " + name + ", age = " + age);
        return name + " " + age;
    }

    /**
     * 前端请求: http://localhost:8085/param/mul?hbs=吃&hbs=喝
     * <p>
     * 一名多值,可以使用集合接收即可!但是需要使用@RequestParam注解指定
     */
    @GetMapping(value = "/mul")
    @ResponseBody
    public Object mulForm(@RequestParam List<String> hbs) {
        System.out.println("hbs = " + hbs);
        return hbs;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(User user) {
        // 在这里可以使用 user 对象的属性来接收请求参数
        System.out.println("user = " + user);
        return "success";

    }


}