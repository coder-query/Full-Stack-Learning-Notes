package com.it.test.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MyMetaHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开始填充 --- MyMetaHandler - insertFill");
        // 修正：使用实体类的字段名（驼峰命名）
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("开始填充 --- MyMetaHandler - updateFill");
        // 修正：使用正确的更新方法 + 实体类字段名
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}