package com.example.nacos_config_bootstrap.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 */
@Component
@RefreshScope
public class BootStrapController implements CommandLineRunner {

  @Value("${config.info}")
  private String info;

  @Override
  public void run(String... args) {
    System.out.println("Hello World! " + info + "1212121");
  }
}
