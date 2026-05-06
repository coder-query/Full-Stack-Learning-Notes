package org.shuai.boot.shirocodestudy.service;

public interface UserService {
    String addUser(String username, String password);

    String deleteUser(String username, String password);

    String updateUser(String username, String password);

    String getUser(String username, String password);
}
