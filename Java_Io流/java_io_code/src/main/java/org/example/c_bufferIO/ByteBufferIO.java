package org.example.c_bufferIO;

import java.io.*;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/9/17 0017
 */
public class ByteBufferIO {
    public static void main(String[] args){
        System.out.println("BufferIO");
        System.out.println("当前工作目录: " + System.getProperty("user.dir"));

        try(
                // 原始字节输入流
                InputStream is = new FileInputStream("Java_Io流/java_io_code/temp/in-zsh-01.txt");
                // 缓冲输入流（包装原始流，提供缓冲功能）
                BufferedInputStream bis = new BufferedInputStream(is);
                // 原始字节输出流
                OutputStream os = new FileOutputStream("Java_Io流/java_io_code/temp/in-zsh-02.txt");
                // 缓冲输出流（包装原始流，提供缓冲功能）
                BufferedOutputStream bos = new BufferedOutputStream(os);
        ) {
            // 缓冲区（缓冲流内部已有缓冲区，这里的数组是应用层缓冲区，可进一步提升效率）
            byte[] buffer = new byte[1024*8];
            int len; // 存储实际读取的字节数

            // 循环读取：bis.read()会从缓冲区读取数据，返回-1表示读取结束
            while ((len = bis.read(buffer)) != -1) {
                // 写入实际读取的字节数（避免写入空字节）
                bos.write(buffer, 0, len);
                // 缓冲输出流会自动刷新缓冲区，除非需要立即写入，否则无需手动调用flush()
            }
            System.out.println("缓冲流复制完成");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
