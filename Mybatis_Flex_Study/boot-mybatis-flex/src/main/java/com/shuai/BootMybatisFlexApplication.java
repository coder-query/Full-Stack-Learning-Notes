package com.shuai;

import cn.dev33.satoken.SaManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shuai.mapper")
public class BootMybatisFlexApplication {
  public static void main(String[] args) {
    SpringApplication.run(BootMybatisFlexApplication.class, args);
    System.out.println("启动成功：sa-token配置如下：" + SaManager.getConfig());
  }
}
