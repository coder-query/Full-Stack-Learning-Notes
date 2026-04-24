package com.shuai;

import cn.dev33.satoken.SaManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@SpringBootApplication
@MapperScan("com.shuai.mapper")
@Slf4j
public class BootMybatisFlexApplication {
  @SneakyThrows
  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    SpringApplication springApplication = new SpringApplication(BootMybatisFlexApplication.class);
    ConfigurableApplicationContext applicationContext = springApplication.run(args);
    log.info("\n" + "    /\\_____/\\\n" +
            "   /  o   o  \\\n" +
            "  ( ==  v  == )\n" +
            "   )         (\n" +
            "  (           )\n" +
            " ( (  )   (  ) )\n" +
            "(__(__)___(__)__)");
    Environment env = applicationContext.getEnvironment();
    log.info("\n----------------------------------------------------------\n\t" +
                    "SpringBootApplication is running! Access URLs:\n\t" +
                    "Local: \t\thttp://localhost:{}\n\t" +
                    "External: \thttp://{}:{}\n\t" +
                    "Doc: \thttp://{}:{}/doc.html\n" +
                    "----------------------------------------------------------",
            env.getProperty("server.port"),
            InetAddress.getLocalHost().getHostAddress(),
            env.getProperty("server.port"),
            InetAddress.getLocalHost().getHostAddress(),
            env.getProperty("server.port"));
    log.info(("spring boot 2.6.6 + jedis 操作 redis  (已启动) 耗时: " + (System.currentTimeMillis() - startTime)) + "ms");
    System.out.println("启动成功：sa-token配置如下：" + SaManager.getConfig());
  }
}
