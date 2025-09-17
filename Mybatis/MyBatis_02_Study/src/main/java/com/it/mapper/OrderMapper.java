package com.it.mapper;

import com.it.pojo.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface OrderMapper {
    
    Order selectOrderWithCustomer(@Param("orderId") Integer orderId);
}