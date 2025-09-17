package com.it.service.Impl;

import com.alibaba.nacos.shaded.io.grpc.LoadBalancer;
import com.it.feign.ProductFeignClient;
import com.it.pojo.order.Order;
import com.it.pojo.stock.Product;
import com.it.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @Title: 心动的offer->13k
 * @Author 帅宏编码-coding
 * @Date 2025/1/31 星期五 14:31
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    LoadBalancerClient loadBalancer;

    @Autowired
    ProductFeignClient productFeignClient;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Order createOrder(Integer productId, Integer userId) {

        //使用restTemplate 调用远程服务
//        Product product = getProductFromRemote(productId);

        //使用负载均衡方式一
//        Product product = getProductFromRemoteWithLoadBalancer(productId);

        //使用负载均衡方式二
//        Product product = getProductFromRemoteWithLoadBalancerAnnotation(productId);

//        使用OpenFeign 调用远程服务
        Product product = productFeignClient.getProductById(productId);
        Order order = new Order();
        order.setId(productId);
        //计算总金额
        order.setTotalAmount(product.getProductPrice().multiply(new BigDecimal(product.getProductNum())));
        order.setUserId(userId);
        order.setAddress("帅宏的home");
        //商品列表
        order.setProductList(Arrays.asList(product));
        return order;
    }

    private Product getProductFromRemote(Integer productId) {
        List<ServiceInstance> instanceList = discoveryClient.getInstances("service-stock");
        ServiceInstance instance = instanceList.get(0);
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/stock/getProduct/" + productId;
        System.out.println("url:" + url);
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

    //    使用负载均衡方式一
    private Product getProductFromRemoteWithLoadBalancer(Integer productId) {
        ServiceInstance choose = loadBalancer.choose("service-stock");
        String url = "http://" + choose.getHost() + ":" + choose.getPort() + "/stock/getProduct/" + productId;
        System.out.println("url:" + url);
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

    //    使用负载均衡方式二
    private Product getProductFromRemoteWithLoadBalancerAnnotation(Integer productId) {
        String url = "http://service-stock/stock/getProduct/" + productId;
        System.out.println("url:" + url);
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }

}
