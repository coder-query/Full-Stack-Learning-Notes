package com.example.nacos_config_bootstrap.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class BootStrapController implements CommandLineRunner {

    @NacosValue(value = "${zsh.datasource.url}")
    private String nacosValueUrl;
    
    @Value("${zsh.datasource.url}")
    private String springValueUrl;
    
    @Autowired
    private Environment environment;

    @Override
    public void run(String... args) {
        // 比较三种方式获取的值
        String envValue = environment.getProperty("zsh.datasource.url");
        
        System.out.println("Environment: " + envValue);
        System.out.println("@Value: " + springValueUrl);
        System.out.println("@NacosValue: " + nacosValueUrl);
        
        // 检查 Nacos 配置服务
//        checkNacosConfigDirectly();
    }
    
    private void checkNacosConfigDirectly() {
        try {
            // 直接通过 Nacos ConfigService 获取配置
            // 这需要注入 ConfigService
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}