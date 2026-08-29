package com.shuai.bootmapstruct.auto;

import com.shuai.bootmapstruct.common.entity.UserEntity;
import com.shuai.bootmapstruct.common.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 类功能：
 *
 * @author shuaihong-coding
 * @date 2026-01-12 18:04
 */

@Component
public class BeanUtilsAutoDemo implements CommandLineRunner {
    @Override
    public void run(String... args) {

        UserEntity userEntity = new UserEntity()
                .setId("1")
                .setAge(16)
                .setUsername("zsh")
                .setPassword("112233PWD");

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userEntity,userVO);

        System.out.println("userVO =======> " + userVO);

    }
}
