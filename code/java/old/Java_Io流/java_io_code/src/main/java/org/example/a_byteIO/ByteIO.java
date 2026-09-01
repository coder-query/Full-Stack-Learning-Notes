package org.example.a_byteIO;

import java.io.*;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/9/17 0017
 */
public class ByteIO {
    public static void main(String[] args) {
        System.out.println("ByteIO");
        System.out.println("当前工作目录: " + System.getProperty("user.dir"));
        try (
                InputStream is = new FileInputStream("Java_Io流/java_io_code/temp/in-zsh-01.txt");
                OutputStream os = new FileOutputStream("Java_Io流/java_io_code/temp/in-zsh-02.txt")
        ) {
            byte[] bytes = new byte[1024];
            int len; // 存储「实际读取的字节数」
            while ((len = is.read(bytes)) != -1) {
                os.write(bytes, 0, len); // 只写「实际读取的长度」的字节
                os.flush(); // 可省略，try-with-resources 关闭流时会自动刷新
            }
            System.out.println("复制完成");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}