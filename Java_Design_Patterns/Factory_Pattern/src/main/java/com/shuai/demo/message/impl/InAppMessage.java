package com.shuai.demo.message.impl;

import com.shuai.demo.message.Message;

public class InAppMessage implements Message {
  @Override
  public void send(String target, String content) {
    System.out.println(InAppMessage.class + " ===> 发送站内信给 " + target + "，内容：" + content);
  }
}
