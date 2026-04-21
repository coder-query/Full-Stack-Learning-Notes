package org.shuai;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.shuai.mapper")
@Slf4j
public class RedisCodeApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisCodeApplication.class, args);
        log.info("\n" + "    /\\_____/\\\n" +
                "   /  o   o  \\\n" +
                "  ( ==  v  == )\n" +
                "   )         (\n" +
                "  (           )\n" +
                " ( (  )   (  ) )\n" +
                "(__(__)___(__)__)");
    }
}
