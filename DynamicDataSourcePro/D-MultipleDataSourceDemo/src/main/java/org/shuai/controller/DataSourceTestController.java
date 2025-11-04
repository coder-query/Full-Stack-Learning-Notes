package org.shuai.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.shuai.mapper.master.MasterDataSourceMapper;
import org.shuai.mapper.slave.SlaveDataSourceMapper;
import org.shuai.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataSourceTestController {

    @Resource private MasterDataSourceMapper masterDataSourceMapper;
    @Resource private SlaveDataSourceMapper slaveDataSourceMapper;

    @RequestMapping("/test/master/{id}")
    public String testMasterDataSource(@PathVariable Integer id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User userByIdWithMaster = masterDataSourceMapper.getUserByIdWithMaster(id);
        System.out.println("userByIdWithMaster = " + userByIdWithMaster);
        return objectMapper.writeValueAsString(userByIdWithMaster);
    }
    @RequestMapping("/test/slave/{id}")
    public String testSlaveDataSource(@PathVariable Integer id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User userByIdWithSlave = slaveDataSourceMapper.getUserByIdWithSlave(id);
        System.out.println("userByIdWithSlave = " + userByIdWithSlave);
        return objectMapper.writeValueAsString(userByIdWithSlave);
    }
}
