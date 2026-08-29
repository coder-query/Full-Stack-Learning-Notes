package org.shuai.boot_mongodb_mongoplus_code.mapper;

import com.mongoplus.mapper.MongoMapper;
import org.shuai.boot_mongodb_mongoplus_code.model.entity.User;

// mapper接口需要继承`MongoMapper`接口，并传入所属实体泛型
// solon需要在此处标注 @Mongo注解
public interface UserMongoMapper extends MongoMapper<User> {}
