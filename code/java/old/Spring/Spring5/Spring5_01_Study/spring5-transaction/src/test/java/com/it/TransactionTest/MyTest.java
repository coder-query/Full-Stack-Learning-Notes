package com.it.TransactionTest;

import com.alibaba.druid.pool.DruidDataSource;
import com.it.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Title: 心动的offer->17k
 * @Author 帅宏编码-coding
 * @Date 2025/1/19 星期日 23:26
 */
public class MyTest {
    ApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void test_druidDataSource() {
        DruidDataSource druidDataSource = context.getBean(DruidDataSource.class);
        System.out.println(druidDataSource);
    }

    @Test
    public void test_query_count() {
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        String sql = "select count(*) from user";
        Long count = jdbcTemplate.queryForObject(sql, Long.class);
        System.out.println("当前数据表中有" + count + "个用户");
    }

    /*
     * @Title: test_add_use
     * @Description: 新增一个用户
     */
    @Test
    public void test_add_user() {
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        String sql = "insert into user(id,name,pwd) values(?,?,?)";
        jdbcTemplate.update(sql, 4,"小明", "123456");
        System.out.println("添加成功");
    }

    /*
     * @Title:  test_update_user
     * @Description: 修改一个用户
     */
    @Test
    public void test_update_user() {
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        String sql = "update user set name=?,pwd=? where id=?";
        jdbcTemplate.update(sql, "小红", "123456", 1);
        System.out.println("修改成功");
    }

    /*
     * @Title: test_delete_user
     * @Description: 删除一个用户
     */
    @Test
    public void test_delete_user() {
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        String sql = "delete from user where id=?";
        jdbcTemplate.update(sql, 1);
        System.out.println("删除成功");
    }

    /*
    * @Title: test_query_user_01
    * @Description: 查询单个实例
     */
    @Test
    public void test_query_user_01() {
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        String sql = "select * from user where id = 1";

        ////如果数据表和实体类名字对应,可以不需要手动设置,使用BeanPropertyRowMapper自动映射对象
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class));

//        //如果数据表和实体类名字不对应,可以手动设置
//        User user = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
//            User user1 = new User();
//            user1.setId(rs.getInt("id"));
//            user1.setName(rs.getString("name"));
//            user1.setPwd(rs.getString("pwd"));
//            return user1;
//        });

        System.out.println(user);
    }


    /*
     * @Title: test_query_user_02
     * @Description: 查询多个实例
     */
    @Test
    public void test_query_user_02() {
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        String sql = "select * from user";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        for (User user : userList) {
            System.out.println(user);
        }
    }

}
