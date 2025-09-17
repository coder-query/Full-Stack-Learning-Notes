package com.it.mapper;

import com.it.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/3/20 星期四 19:41
 */
@Mapper
public interface UserMapper {
	@Select("select * from user_springboot where id = #{userId}")
	User getUserById(@Param("userId") Integer id);
}
