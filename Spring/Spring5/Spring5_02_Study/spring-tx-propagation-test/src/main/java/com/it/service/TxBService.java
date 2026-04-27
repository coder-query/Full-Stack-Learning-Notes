package com.it.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/9 星期三 13:15
 */
@Service
public class TxBService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(propagation = Propagation.SUPPORTS)
	public void updateStudentClazzById(Integer id) {
		jdbcTemplate.update("update test_students_tx set clazz=? where stu_id=?", "中国", id);
		String transactionName = TransactionSynchronizationManager.getCurrentTransactionName();
		System.out.println("TxBService加入的事务为  :  " + transactionName);
		if (1 == 1) {
			throw new RuntimeException();
		}
	}
}
