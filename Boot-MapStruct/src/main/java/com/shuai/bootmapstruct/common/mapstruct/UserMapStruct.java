package com.shuai.bootmapstruct.common.mapstruct;

import com.shuai.bootmapstruct.common.entity.UserEntity;
import com.shuai.bootmapstruct.common.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 类功能：
 *
 * @author shuaihong-coding
 * @date 2026-01-12 18:10
 */
@Mapper
public interface UserMapStruct {
    UserMapStruct INSTANCE = Mappers.getMapper( UserMapStruct.class );


//    @Mappings({
//            @Mapping(source = "username",target = "nickName"),
//            @Mapping(source = "password",target = "pwd"),
//            @Mapping(target = "id",ignore = true),
//            @Mapping(defaultValue = "20",target = "age")
//
//    })
    UserVO entityToVo(UserEntity userEntity);
}
