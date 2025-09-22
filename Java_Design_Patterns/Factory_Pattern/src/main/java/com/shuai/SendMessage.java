package com.shuai;

import com.shuai.demo.factory.MessageFactory;
import com.shuai.demo.factory.impl.InAppMessageFactory;
import com.shuai.demo.service.NotificationService;

public class SendMessage {
  public static void main(String[] args) {
    MessageFactory factory = new InAppMessageFactory(); // 切换只需换这里
    NotificationService service = new NotificationService(factory);
    service.notifyUser("13812345678", "您的验证码是 123456");
  }
}
