package org.shuai.ImplementDataSourceDemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.shuai.ImplementDataSourceDemo.model.User;

@Mapper
public interface DataSourceMapper {
    User getUserByIdWithMaster(Integer id);
    User getUserByIdWithSlave(Integer id);
}
