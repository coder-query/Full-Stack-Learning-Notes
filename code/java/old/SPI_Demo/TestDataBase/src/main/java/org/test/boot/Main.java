package org.test.boot;

import org.shuai.boot.jdbc.MyDataBaseConnection;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        ServiceLoader<MyDataBaseConnection> load = ServiceLoader.load(MyDataBaseConnection.class);
        Iterator<MyDataBaseConnection> iterator = load.iterator();
        while (iterator.hasNext()) {
            MyDataBaseConnection next = iterator.next();
            next.createConnection();
        }
    }
}