package com.shuai.demo.factory.impl;

import com.shuai.demo.factory.MessageFactory;
import com.shuai.demo.message.Message;
import com.shuai.demo.message.impl.EmailMessage;

public class EmailMessageFactory implements MessageFactory {
  @Override
  public Message createMessage() {
    return new EmailMessage();
  }
}
