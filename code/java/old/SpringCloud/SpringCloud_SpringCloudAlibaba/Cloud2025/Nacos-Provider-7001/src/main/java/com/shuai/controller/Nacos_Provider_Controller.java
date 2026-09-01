package com.shuai.controller;

import com.shuai.service.ProductServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
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

  @Resource private ProductServiceImpl productService;

  @RequestMapping(value = "/helloNacosProvider", method = RequestMethod.GET)
  public Map<String, String> sayHello() {
    System.out.println("Nacos Provider 7001 被调用了。。。");
    Map<String, String> map = new HashMap<>();
    map.put("message", "hello Nacos 我是 Provider 7001");
    return map;
  }

  @RequestMapping(value = "/order/query", method = RequestMethod.GET)
  public Map<String, String> queryOrder() {
    System.out.println("Nacos Provider 7001 被调起了。。。");
    System.out.println(productService.getProducts());
    System.out.println("刚刚查完商品信息，现在查询订单信息。。。");
    Map<String, String> map = new HashMap<>();
    map.put("message", "查询订单信息");
    return map;
  }


  @RequestMapping(value = "/order/update", method = RequestMethod.GET)
  public Map<String, String> updateOrder() {
    System.out.println("Nacos Provider 7001 被调起了。。。");
    Map<String, String> map = new HashMap<>();
    map.put("message", "更新订单信息");
    return map;
  }

  @RequestMapping(value = "/order/add", method = RequestMethod.POST)
  public Map<String, String> addOrder() {
    System.out.println("Nacos Provider 7001 被调起了。。。");
    System.out.println(productService.getProducts());
    System.out.println("刚刚查完商品信息，现在添加订单信息。。。");
    Map<String, String> map = new HashMap<>();
    map.put("message", "添加订单信息");
    return map;
  }
}
