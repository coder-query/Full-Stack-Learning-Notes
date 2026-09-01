package org.shuai.mapper.slave;

import org.apache.ibatis.annotations.Mapper;
import org.shuai.model.User;


@Mapper
public interface SlaveDataSourceMapper {
    User getUserByIdWithSlave(Integer id);
}
