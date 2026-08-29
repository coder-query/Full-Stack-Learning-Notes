package com.shuai.demo.message;

// 抽象产品接口：通知消息
public interface Message {
  void send(String target, String content);
}
