package com.org.zsh.thymeleafcode;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@Slf4j
@Data
@SpringBootApplication
@MapperScan("com.org.zsh.thymeleafcode.mapper")
public class ThymeleafCodeApplication {

        @SneakyThrows
        public static void main(String[] args) {
            long startTime = System.currentTimeMillis();
            SpringApplication springApplication = new SpringApplication(ThymeleafCodeApplication.class);
            ConfigurableApplicationContext applicationContext = springApplication.run(args);
            System.out.println("    /\\_____/\\\n" +
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
            log.info(("ThymeleafCodeApplication (已启动) 耗时: " + (System.currentTimeMillis() - startTime)) + "ms");
            System.out.println(("ThymeleafCodeApplication (已启动) 耗时: " + (System.currentTimeMillis() - startTime)) + "ms");
        }
}
