package com.shuai.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 */
@RestController
public class Nacos_Rest_Template_Controller {

  @Resource private RestTemplate restTemplate;

  @Value("${rest.server.url}")
  private String serverUrl;

  @RequestMapping(value = "/helloRestConsumer", method = RequestMethod.GET)
  public String sayHello() {
    System.out.println("Rest Consumer 被调用了。。。");
    return restTemplate.getForObject(serverUrl + "/helloNacosProvider", String.class);
  }
}
