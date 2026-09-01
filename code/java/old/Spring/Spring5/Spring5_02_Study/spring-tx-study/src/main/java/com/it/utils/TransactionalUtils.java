package com.it.utils;

import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

/**
 * @author 帅宏-coding
 * @Money java_offer_13k
 * @date 2025/4/9 星期三 10:48
 */
@Component
public class TransactionalUtils {

	@Resource
	private JdbcTransactionManager jdbcTransactionManager;

	public TransactionStatus begin() {
		TransactionStatus transaction =
				jdbcTransactionManager.getTransaction(new DefaultTransactionDefinition());
		return transaction;
	}

	public void commit(TransactionStatus transaction) {
		jdbcTransactionManager.commit(transaction);
	}

	public void rollback(TransactionStatus transaction) {
		jdbcTransactionManager.rollback(transaction);
	}
}
