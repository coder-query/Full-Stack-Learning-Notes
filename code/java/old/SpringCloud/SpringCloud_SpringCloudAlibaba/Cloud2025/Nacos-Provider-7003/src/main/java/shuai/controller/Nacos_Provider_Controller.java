package shuai.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 */
@RestController
public class Nacos_Provider_Controller {

  @RequestMapping(value = "/helloNacosProvider", method = RequestMethod.GET)
  public Map<String, String> sayHello() {
    System.out.println("Nacos Provider 7003 被调用了。。。");
    Map<String, String> map = new HashMap<>();
    map.put("message", "hello Nacos 我是 Provider 7003");
    return map;
  }
}
