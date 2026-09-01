package org.shuai.boot.jdbc;

public class OracleConnection implements MyDataBaseConnection{
    @Override
    public void createConnection() {
        System.out.println("Oracle Connection 已接入，可以进行 Oracle 的数据库操作");
    }
}
