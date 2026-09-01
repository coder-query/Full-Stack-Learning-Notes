package org.shuai;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@Slf4j
//# 当前项目使用 MyBatis-Flex，不使用 Spring Data Web 的 Pageable/Sort/投影参数解析。
//        # 关闭该自动配置可避免 projectingArgumentResolverBeanPostProcessor 过早初始化 Shiro/MyBatis 等 Bean 导致启动 WARN。
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration.class
})
@MapperScan(basePackages = {"org.shuai.sys.mapper"})
public class ShiroBoot3Application {

    @SneakyThrows
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication springApplication = new SpringApplication(ShiroBoot3Application.class);
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
        log.info(("ShiroBoot3Application (已启动) 耗时: " + (System.currentTimeMillis() - startTime)) + "ms");
        System.out.println(("ShiroBoot3Application (已启动) 耗时: " + (System.currentTimeMillis() - startTime)) + "ms");
    }

}
