package com.it.service;

import com.it.dao.StudentDao;
import com.it.pojo.Student;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class B_Service {

  @Resource private StudentDao studentDao;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void changeInfo() {
    studentDao.updateNameById("B_Service修改成功", 2);
    String name = TransactionSynchronizationManager.getCurrentTransactionName();
    System.out.println("B_Service加入的事务名称：" + name);
    //    if (1 == 1) {
    //      throw new RuntimeException();
    //    }
  }

  public Student getStudentById(Integer id) {
    return studentDao.getStudentById(id);
  }

  public List<Student> getAllStudents() {
    return studentDao.getAllStudents();
  }
}
