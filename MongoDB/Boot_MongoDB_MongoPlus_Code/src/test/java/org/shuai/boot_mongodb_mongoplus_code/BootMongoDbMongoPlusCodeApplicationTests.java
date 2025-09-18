package org.shuai.boot_mongodb_mongoplus_code;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.shuai.boot_mongodb_mongoplus_code.model.entity.User;
import org.shuai.boot_mongodb_mongoplus_code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootMongoDbMongoPlusCodeApplicationTests {

  @Autowired(required = false)
  private UserService userService;

  @Test
  public void testSelect() {
    System.out.println(("----- selectAll method test ------"));
    List<User> userList = userService.list();
    userList.forEach(System.out::println);
  }
}
