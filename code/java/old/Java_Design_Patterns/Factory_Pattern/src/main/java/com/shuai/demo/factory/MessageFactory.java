package com.shuai.demo.factory;

import com.shuai.demo.message.Message;

// 抽象工厂接口：负责生产消息对象
public interface MessageFactory {
  Message createMessage();
}
