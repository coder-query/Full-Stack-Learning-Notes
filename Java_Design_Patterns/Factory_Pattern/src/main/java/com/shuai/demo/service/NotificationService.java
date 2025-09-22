package com.shuai.demo.service;

import com.shuai.demo.factory.MessageFactory;
import com.shuai.demo.message.Message;

public class NotificationService {
  private final MessageFactory messageFactory;

  public NotificationService(MessageFactory factory) {
    this.messageFactory = factory;
  }

  public void notifyUser(String target, String content) {
    Message message = messageFactory.createMessage();
    message.send(target, content);
  }
}
