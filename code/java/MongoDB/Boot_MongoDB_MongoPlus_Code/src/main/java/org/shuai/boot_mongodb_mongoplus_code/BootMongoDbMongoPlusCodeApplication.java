package org.shuai.boot_mongodb_mongoplus_code;

import com.mongoplus.annotation.MongoMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MongoMapperScan("org.shuai.boot_mongodb_mongoplus_code.mapper")
@SpringBootApplication
public class BootMongoDbMongoPlusCodeApplication {

  public static void main(String[] args) {
    SpringApplication.run(BootMongoDbMongoPlusCodeApplication.class, args);
  }
}
