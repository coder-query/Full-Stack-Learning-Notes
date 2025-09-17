package com.it.static_page.controller;

import com.it.service_common.pojo.Products;
import lombok.CustomLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/page")
public class PageController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/getData/{id}")
    public Products findDataById(@PathVariable Integer id) {

        List<ServiceInstance> instanceList = discoveryClient.getInstances("service-product");
        ServiceInstance serviceInstance = instanceList.get(0);
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();

        String url = "http://"+host+":"+port+"/product/query/"+id;

//        String url = "http://localhost:9000/product/query/" + id;
        Products products =
                restTemplate.getForObject(url,Products.class);
        System.out.println("从service-product获得product对象:" + products);
        return products;
    }

    @GetMapping("/getPort")
    public String testRibbon(){
        String serverName = "service-product";
        String path = "/service/port";
        String url = "http://" + serverName + path;
        return restTemplate.getForObject(url,String.class);
    }

}