package org.example.boot_redis_template_code;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class BootRedisTemplateCodeApplicationTests {

  // 注入RedisTemplate
  @Autowired(required = false)
  private RedisTemplate redisTemplate;

  // String类型
  @Test
  void testString() {
    redisTemplate.opsForValue().set("name", "javaCoder");
    Object name = redisTemplate.opsForValue().get("name");
    System.out.println(name);
  }

  // Hash类型
  @Test
  public void testHash() {
    redisTemplate.opsForHash().put("hash", "name", "abc");
    redisTemplate.opsForHash().put("hash", "age", 18);
    Map map = redisTemplate.opsForHash().entries("hash");
    System.out.println(map);
  }

  // List类型
  @Test
  public void testList() {
    redisTemplate.opsForList().leftPushAll("list", "zhangsan", "li", "wanger");
    List<String> names = redisTemplate.opsForList().range("list", 0, -1);
    System.out.println(names);
  }

  // Set类型
  @Test
  public void testSet() {
    redisTemplate.opsForSet().add("set", "cat", "dog", "wolf", "pig", "sheep");
    Set<String> set = redisTemplate.opsForSet().members("set");
    System.out.println(set);
  }

  // SortedSet类型
  @Test
  public void testSortedSet() {
    redisTemplate.opsForZSet().add("zset", "cat", 30);
    redisTemplate.opsForZSet().add("zset", "dog", 20);
    redisTemplate.opsForZSet().add("zset", "wolf", 80);
    redisTemplate.opsForZSet().add("zset", "pig", 40);
    Set<String> aClass = redisTemplate.opsForZSet().range("zset", 0, -1);
    System.out.println(aClass);

    // 使用下面这套写法，也行
    // Set<ZSetOperations.TypedTuple<String>> set = new HashSet<>();
    // set.add(new DefaultTypedTuple<>("cat", 30.0));
    // set.add(new DefaultTypedTuple<>("dog", 20.0));
    // set.add(new DefaultTypedTuple<>("wolf", 80.0));
    // set.add(new DefaultTypedTuple<>("pig", 40.0));
    // redisTemplate.opsForZSet().add("zset", set);
    // Set<String> aClass1 = redisTemplate.opsForZSet().range("zset",
    // 0, -1);
    // System.out.println(aClass1);
  }
}
