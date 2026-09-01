package org.shuai.controller.testcc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Api(tags = "测试")
public class TestController {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @GetMapping("/hello")
    @ApiOperation(value = "测试接口")
    public String hello() throws JsonProcessingException {
        // oop
        redisTemplate.opsForValue().set("name666", "我是java工程师");
        Object name = redisTemplate.opsForValue().get("name");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(name);
    }
}
