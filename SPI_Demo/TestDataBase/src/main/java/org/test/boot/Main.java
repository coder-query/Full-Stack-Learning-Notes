package org.test.boot;

import org.shuai.boot.jdbc.MyDataBaseConnection;

import java.util.Iterator;
import java.util.ServiceLoader;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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