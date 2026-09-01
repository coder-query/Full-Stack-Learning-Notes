package com.it.service;

import com.it.annotation.MyTransactional;
import com.it.dao.StudentDao;
import com.it.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

//    @MyTransactional

    @Transactional
    public void changeInfo() {

        studentDao.updateAgeById(100, 1);  // sql_1

        System.out.println("-----------");

//            System.out.println(1 / 0);

        studentDao.updateNameById("test", 1);  // sql_2


    }


    public Student getStudentById(Integer id) {
        return studentDao.getStudentById(id);
    }

    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

}
