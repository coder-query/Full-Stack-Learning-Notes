package com.shuai;

import com.shuai.SpringbootApplicationShardingJdbcSimple;
import com.shuai.it.mapper.DictMapper;
import com.shuai.it.mapper.OrderMapper;
import com.shuai.it.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/6/4 0004
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringbootApplicationShardingJdbcSimple.class})
public class SpringbootApplicationShardingJdbcSimpleTest {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    DictMapper dictMapper;

    @Test
    public void testInsertOrder() {
        for (int i = 0; i < 10; i++) {
            orderMapper.insertOrder(new BigDecimal((i + 1) * 5), 1L, "WAIT_PAY");
        }
        for (int i = 0; i < 10; i++) {
            orderMapper.insertOrder(new BigDecimal((i + 1) * 5), 2L, "WAIT_PAY");
        }
    }

    @Test
    public void testSelectOrderByIds() {
        List<Long> ids = new ArrayList<>();
        ids.add(1137011817508241409L);
        ids.add(1137011818418405376L);
        List<Map> maps = orderMapper.selectOrderByIds(ids);
        System.out.println(maps);
    }

    @Test
    public void testInsertUser() {
        for (int i = 0; i < 10; i++) {
            Long id = i + 1L;
            userMapper.insertUser(id, "姓名" + id);
        }
    }

    @Test
    public void testSelectUserByIds() {
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        List<Map> users = userMapper.selectUserByIds(userIds);
        System.out.println(users);
    }

    @Test
    public void testInsertDict() {
        dictMapper.insertDict(1L, "user_type", "0", "管理员");
        dictMapper.insertDict(2L, "user_type", "1", "操作员");
    }

    @Test
    public void testDeleteDict() {
        dictMapper.deleteDict(1L);
        dictMapper.deleteDict(2L);
    }

}
