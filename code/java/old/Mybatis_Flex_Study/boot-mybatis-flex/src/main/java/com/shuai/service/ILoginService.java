package com.shuai.service;


import com.shuai.model.User;

/// **
// * @author : 帅宏-coding
// * @version : 1.0
// * @date : 2025/7/31 0031
// */
public interface ILoginService {
  User login(String account, String password);
}
