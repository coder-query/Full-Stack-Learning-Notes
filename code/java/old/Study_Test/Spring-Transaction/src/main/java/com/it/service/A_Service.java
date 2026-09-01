package com.it.service;

import com.it.dao.StudentDao;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class A_Service {

  @Resource private StudentDao studentDao;

  @Resource private B_Service bService;

  @Transactional
  public void changeInfo() {
    studentDao.updateNameById("A_Service修改成功", 1);
    String name = TransactionSynchronizationManager.getCurrentTransactionName();
    System.out.println("A_Service加入的事务名称：" + name);
    // 调用B_Service
    bService.changeInfo();
    if (1 == 1) {
      throw new RuntimeException();
    }
  }
}
