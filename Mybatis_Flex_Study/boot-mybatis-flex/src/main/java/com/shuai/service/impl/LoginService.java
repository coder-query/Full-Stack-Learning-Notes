package com.shuai.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.shuai.mapper.AccountMapper;
import com.shuai.model.Account;
import com.shuai.model.table.AccountTableDef;
import com.shuai.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/// **
// * @author : 帅宏-coding
// * @version : 1.0
// * @date : 2025/7/31 0031
// */
@Service
public class LoginService implements ILoginService {

  @Autowired private AccountMapper accountMapper;

  @Override
  public Account login(String account, String password) {

    AccountTableDef ACCOUNT = AccountTableDef.ACCOUNT;
    // 从数据库里查询用户信息
    QueryWrapper queryWrapper =
        QueryWrapper.create()
            .select(ACCOUNT.ALL_COLUMNS)
            .from(ACCOUNT)
            .where(ACCOUNT.ACCOUNT_.eq(account).and(ACCOUNT.PASSWORD.eq(password)));

    Account res = accountMapper.selectOneByQuery(queryWrapper);

    return res;
  }
}
