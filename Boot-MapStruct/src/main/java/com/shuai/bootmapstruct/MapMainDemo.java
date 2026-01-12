package com.shuai.bootmapstruct;

import com.shuai.bootmapstruct.common.entity.UserEntity;
import com.shuai.bootmapstruct.common.mapstruct.UserMapStruct;
import com.shuai.bootmapstruct.common.vo.UserVO;
import org.springframework.beans.BeanUtils;

/**
 * 类功能：
 *
 * @author shuaihong-coding
 * @date 2026-01-12 17:42
 */
public class MapMainDemo {
    public static void main(String[] args) {

        // BeanUtils
        UserEntity userEntity = new UserEntity()
                .setId("1")
//                .setAge(16)
                .setUsername("zsh")
                .setPassword("112233PWD");

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userEntity,userVO);

        System.out.println("userVO =======> " + userVO);


        // MapStruct

        UserVO vo = UserMapStruct.INSTANCE.entityToVo(userEntity);
        System.out.println("MapStruct vo =======> " + vo);
    }
}
