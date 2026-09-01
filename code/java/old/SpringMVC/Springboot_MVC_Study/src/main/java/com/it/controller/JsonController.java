package com.it.controller;

import com.it.pojo.Person;
import org.springframework.web.bind.annotation.*;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/16 星期三
 */
@RestController
@RequestMapping("json")
public class JsonController {

    @PostMapping("/person")
    @ResponseBody
    public String addPerson(@RequestBody Person person) {
        // 在这里可以使用 person 对象来操作 JSON 数据中包含的属性
        return " success " + person;
    }
}
