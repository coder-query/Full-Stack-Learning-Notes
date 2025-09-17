package com.it;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

/**
 * @Title: 心动的offer->13k
 * @Author 帅宏编码-coding
 * @Date 2025/1/31 星期五 13:07
 */
@SpringBootTest
public class stockTest {

    @Autowired
    DiscoveryClient discoveryClient;

    @Test
    public void test() {
        List<String> clientServices = discoveryClient.getServices();
        for (String clientService : clientServices) {
            System.out.println(clientService);
        }
    }
}
