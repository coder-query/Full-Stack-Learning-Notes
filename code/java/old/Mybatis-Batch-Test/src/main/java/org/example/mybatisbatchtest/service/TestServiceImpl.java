package org.example.mybatisbatchtest.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mybatisbatchtest.Test;
import org.example.mybatisbatchtest.mapper.TestMapper;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test>{
}
