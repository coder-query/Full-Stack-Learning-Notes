package com.shuai.service.impl;

import static com.shuai.model.table.UserTableDef.USER;

import com.mybatisflex.core.query.QueryWrapper;
import com.shuai.mapper.UserMapper;
import com.shuai.model.User;
import com.shuai.model.table.TestTableDef;
import com.shuai.model.table.UserTableDef;
import com.shuai.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {

  @Autowired private UserMapper userMapper;

  @Override
  public User login(String account, String password) {

    TestTableDef t = TestTableDef.TEST.as("t");

    UserTableDef u = UserTableDef.USER.as("u");
    // 从数据库里查询用户信息
    QueryWrapper queryWrapper = QueryWrapper.create()
            .select(
                    u.ID,
                    u.USER_ACCOUNT,
                    u.USER_PASSWORD
            )
            .from(u)
            .leftJoin(t)
            .on(u.ID.eq(t.AGE))
            .where(u.USER_ACCOUNT.eq(account)).and(u.USER_PASSWORD.eq(password))
            .groupBy(u.ID);
    User user = userMapper.selectOneByQuery(queryWrapper);

    return user;
  }
}
