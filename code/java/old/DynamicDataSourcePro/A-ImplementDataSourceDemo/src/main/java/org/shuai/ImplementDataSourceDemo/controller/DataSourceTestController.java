package org.shuai.ImplementDataSourceDemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.shuai.ImplementDataSourceDemo.config.MyDynamicDataSourceConfig;
import org.shuai.ImplementDataSourceDemo.constants.DataSourceConstant;
import org.shuai.ImplementDataSourceDemo.mapper.DataSourceMapper;
import org.shuai.ImplementDataSourceDemo.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataSourceTestController {

    @Resource private DataSourceMapper dataSourceMapper;

    @RequestMapping("/test/master/{id}")
    public String testMasterDataSource(@PathVariable Integer id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        MyDynamicDataSourceConfig.dataSourceKey.set(DataSourceConstant.MYSQL_MASTER);
        User userByIdWithMaster = dataSourceMapper.getUserByIdWithMaster(id);
        System.out.println("userByIdWithMaster = " + userByIdWithMaster);
        return objectMapper.writeValueAsString(userByIdWithMaster);
    }
    @RequestMapping("/test/slave/{id}")
    public String testSlaveDataSource(@PathVariable Integer id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        MyDynamicDataSourceConfig.dataSourceKey.set(DataSourceConstant.MYSQL_SLAVE);
        User userByIdWithSlave = dataSourceMapper.getUserByIdWithSlave(id);
        System.out.println("userByIdWithSlave = " + userByIdWithSlave);
        return objectMapper.writeValueAsString(userByIdWithSlave);
    }
}
