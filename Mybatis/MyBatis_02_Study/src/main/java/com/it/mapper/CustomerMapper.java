package com.it.mapper;

import com.it.pojo.Customer;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {

	Customer selectCustomerWithOrderList(@Param("customerId") Integer customerId);

}