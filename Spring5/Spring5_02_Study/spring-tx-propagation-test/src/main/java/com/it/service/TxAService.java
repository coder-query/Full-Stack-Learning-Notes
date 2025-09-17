package com.it.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
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
public class TxAService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private TxBService txBService;
	@Autowired
	private DataSourceTransactionManager transactionManager;

	//	@Transactional
	public void updateStudentAgeById(Integer id) {
		jdbcTemplate.update("update test_students_tx set stu_age=? where stu_id=?", 100, id);
		String transactionName = TransactionSynchronizationManager.getCurrentTransactionName();
		System.out.println("TxAService加入的事务为  :  " + transactionName);
		txBService.updateStudentClazzById(id + 1);
//		if (1 == 1) {
//			throw new RuntimeException();
//		}
	}
}
