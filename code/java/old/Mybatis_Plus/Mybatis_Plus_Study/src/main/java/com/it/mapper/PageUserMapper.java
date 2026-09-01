package com.it.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.it.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/14 星期一
 */
public interface PageUserMapper extends BaseMapper<User> {
	IPage<User> selectUserByAgeLimit(IPage<User> page, @Param("userAge") Integer age);
}
