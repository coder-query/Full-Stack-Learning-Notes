package org.example.b_charIO;

import java.io.*;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/9/17 0017
 */
public class CharIO {
 public static void main(String[] args){
  System.out.println("CharIO");
  System.out.println("当前工作目录: " + System.getProperty("user.dir"));
  try (
          Reader reader = new FileReader("Java_Io流/java_io_code/temp/in-zsh-01.txt");
          Writer writer = new FileWriter("Java_Io流/java_io_code/temp/in-zsh-02.txt")
  ){
    char[] chars = new char[1024];
    int len;
    while (( len = reader.read(chars)) != -1){
     writer.write(chars, 0, len);
     writer.flush();
    }
    System.out.println("复制完成");
  } catch (IOException e) {
   throw new RuntimeException(e);
  }
 }
}
