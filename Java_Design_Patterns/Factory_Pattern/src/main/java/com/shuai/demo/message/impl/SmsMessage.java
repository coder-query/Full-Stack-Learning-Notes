package com.shuai.demo.message.impl;

import com.shuai.demo.message.Message;

public class SmsMessage implements Message {
  @Override
  public void send(String target, String content) {
    System.out.println(SmsMessage.class + " ===> 发送短信给 " + target + "，内容：" + content);
  }
}
