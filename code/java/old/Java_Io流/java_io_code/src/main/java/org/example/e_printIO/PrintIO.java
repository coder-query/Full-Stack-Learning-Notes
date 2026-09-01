package org.example.e_printIO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/9/17 0017
 */
public class PrintIO {
    public static void main(String[] args){
        System.out.println("PrintIO");
        System.out.println("当前工作目录: " + System.getProperty("user.dir"));
        try (PrintWriter pw = new PrintWriter("Java_Io流/java_io_code/temp/print.txt")){
            pw.println("hello world");
            pw.println(123);
            pw.println(true);
            pw.println(123.456);
            pw.println('a');
            pw.println("我是中国人，china，万岁！");
            pw.flush();
            System.out.println("打印完成");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
