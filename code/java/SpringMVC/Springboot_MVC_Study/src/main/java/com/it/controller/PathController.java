package com.it.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/16 星期三
 */
@RestController
@RequestMapping("/path")
public class PathController {
    /**
     * 动态路径设计: /user/{动态部分}/{动态部分}   动态部分使用{}包含即可! {}内部动态标识!
     * 形参列表取值: @PathVariable Long id  如果形参名 = {动态标识} 自动赋值!
     *
     * @PathVariable("动态标识") Long id  如果形参名 != {动态标识} 可以通过指定动态标识赋值!
     * <p>
     * 访问测试:  /path/user/1/root  -> id = 1  uname = root
     */
    @GetMapping("/user/{id}/{name}")
    @ResponseBody
    public String getUser(@PathVariable Integer id,
                          @PathVariable("name") String uname) {
        System.out.println("id = " + id + ", uname = " + uname);
        return "user_detail" + " id = " + id + ", uname = " + uname;
    }
}
