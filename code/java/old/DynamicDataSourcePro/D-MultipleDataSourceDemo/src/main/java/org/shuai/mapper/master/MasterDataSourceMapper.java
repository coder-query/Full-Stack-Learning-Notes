package org.shuai.mapper.master;

import org.apache.ibatis.annotations.Mapper;
import org.shuai.model.User;


@Mapper
public interface MasterDataSourceMapper {
    User getUserByIdWithMaster(Integer id);
}
