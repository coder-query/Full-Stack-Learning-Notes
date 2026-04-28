package org.mysql.boot.jdbc;

import org.shuai.boot.jdbc.MyDataBaseConnection;

public class MysqlConnection implements MyDataBaseConnection {
    @Override
    public void createConnection() {
        System.out.println("Mysql Connection 已接入，可以进行mysql的数据库操作");
    }
}
