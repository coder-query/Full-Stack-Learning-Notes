package com.shuai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 */
@RestController
public class Nacos_Provider_Controller {

  @RequestMapping(value = "/helloNacosProvider", method = RequestMethod.GET)
  public String sayHello() {
    System.out.println("Nacos Provider 被调用了。。。");
    return "hello Nacos Provider";
  }
}
