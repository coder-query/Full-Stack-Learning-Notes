import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.util.Slowlog;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = org.example.JedisPoolApplication.class)
public class JedisPoolApplicationTest {

    @Resource
    private JedisPool jedisPool;

    @Test
    public void testString() {
        try(Jedis jedis = jedisPool.getResource()){
            jedis.set("name", "zs");
            System.out.println(jedis.get("name"));
        }
    }
    @Test
    public void testPipeline() {
        try(Jedis jedis = jedisPool.getResource()){
            ArrayList<String> keyList = CollUtil.newArrayList("k1", "k2", "k3");
            ArrayList<String> valList = CollUtil.newArrayList("v1", "v2", "v3");
            Pipeline pipelined = jedis.pipelined();
            for (int i = 0; i < keyList.size(); i++) {
                pipelined.set(keyList.get(i), valList.get(i));
            }
            System.out.println(pipelined.syncAndReturnAll());
        }
    }
    @Test
    public void testSlowLog() {
        try(Jedis jedis = jedisPool.getResource()){
            List<Slowlog> slowlogs = jedis.slowlogGet(10);
            System.out.println(JSON.toJSONString(slowlogs));
        }
    }
}
