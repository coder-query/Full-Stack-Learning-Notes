package com.shuai.demo.message.impl;

import com.shuai.demo.message.Message;

public class EmailMessage implements Message {
  @Override
  public void send(String target, String content) {
    System.out.println(EmailMessage.class + " ===> 发送邮件给 " + target + "，内容：" + content);
  }
}
