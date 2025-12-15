package org.example.mybatisbatchtest.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.mybatisbatchtest.Test;

import java.util.List;

/**
* @author 27986
* @description 针对表【t_test】的数据库操作Mapper
* @createDate 2025-12-15 15:37:04
* @Entity generator.domain.Test
*/
public interface TestMapper extends BaseMapper<Test> {
    // 批量新增
    int insertBatch(List<Test>  list);
}




