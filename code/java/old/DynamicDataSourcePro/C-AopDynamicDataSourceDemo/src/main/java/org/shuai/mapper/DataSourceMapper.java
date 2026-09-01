package org.shuai.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.shuai.model.User;


@Mapper
public interface DataSourceMapper {
    User getUserByIdWithMaster(Integer id);
    User getUserByIdWithSlave(Integer id);
}
