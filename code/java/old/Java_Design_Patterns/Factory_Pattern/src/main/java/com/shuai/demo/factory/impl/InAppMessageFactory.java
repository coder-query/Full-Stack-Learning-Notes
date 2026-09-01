package com.shuai.demo.factory.impl;

import com.shuai.demo.factory.MessageFactory;
import com.shuai.demo.message.Message;
import com.shuai.demo.message.impl.InAppMessage;

public class InAppMessageFactory implements MessageFactory {
  @Override
  public Message createMessage() {
    return new InAppMessage();
  }
}
