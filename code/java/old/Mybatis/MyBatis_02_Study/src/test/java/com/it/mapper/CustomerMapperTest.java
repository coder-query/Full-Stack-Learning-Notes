package com.it.mapper;

import com.it.pojo.Customer;
import com.it.pojo.Order;
import com.it.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/13 星期日
 */
public class CustomerMapperTest {
    private static SqlSession sqlSession = MybatisUtils.getSqlSession();

    private static void commit() {
        sqlSession.commit();
    }

    @Test
    public void testSelectCustomerWithOrderList() {
        sqlSession = MybatisUtils.getSqlSession();
        CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);
        Customer customer = customerMapper.selectCustomerWithOrderList(1);
        System.out.println("customer.getCustomerId() = " + customer.getCustomerId());
        System.out.println("customer.getCustomerName() = " + customer.getCustomerName());
        List<Order> orderList = customer.getOrderList();
        System.out.println("用户小张购买了以下商品:");
        for (Order order : orderList) {
            System.out.print(order.getOrderName() + " ");
        }
        System.out.println();
        System.out.println("-----------------------------");
    }

    @After
    public void closeSqlSession() {
        sqlSession.close();
    }
}
