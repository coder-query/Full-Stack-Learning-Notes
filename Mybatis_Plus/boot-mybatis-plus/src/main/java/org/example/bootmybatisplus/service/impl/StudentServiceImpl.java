package org.example.bootmybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Student;
import org.example.bootmybatisplus.service.StudentService;
import org.example.bootmybatisplus.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author 27986
* @description 针对表【student】的数据库操作Service实现
* @createDate 2025-12-17 16:31:32
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




