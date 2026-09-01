package org.example.d_transformIO;

import java.io.*;

/**
 * @author : 帅宏-coding
 * @version : 1.0
 * @date : 2025/9/17 0017
 */
public class QuestionDemo {
 public static void main(String[] args){
  System.out.println("QuestionDemo");
  System.out.println("当前工作目录: " + System.getProperty("user.dir"));
  try (
          Reader reader = new FileReader("Java_Io流/java_io_code/temp/GBK-zsh.txt");
  ){
      char[] chars = new char[1024];
      int len;
      while ((len = reader.read(chars)) != -1){
          System.out.println("内容 ： "+new String(chars,0,len));
      }
  } catch (IOException e) {
   throw new RuntimeException(e);
  }
 }
}
