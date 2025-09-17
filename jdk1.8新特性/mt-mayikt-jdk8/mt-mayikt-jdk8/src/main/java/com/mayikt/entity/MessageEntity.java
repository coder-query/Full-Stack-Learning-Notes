package com.mayikt.entity;

public class MessageEntity {
    private String messageId;
    private String messageName;

    public MessageEntity(){

    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "messageId='" + messageId + '\'' +
                ", messageName='" + messageName + '\'' +
                '}';
    }
}