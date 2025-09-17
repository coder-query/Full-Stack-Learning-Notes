package com.shuai.service;

import com.shuai.model.Account;

/// **
// * @author : 帅宏-coding
// * @version : 1.0
// * @date : 2025/7/31 0031
// */
public interface ILoginService {
  Account login(String account, String password);
}
