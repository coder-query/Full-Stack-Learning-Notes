package com.roy.rabbitmq.basic;

import com.rabbitmq.client.*;
import com.roy.rabbitmq.RabbitMQUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @auth roykingw
 */
public class FirstConsumer {

    public static void main(String[] args) throws Exception{
        /**
         * 1. 基础的模型 (申明队列) p -> 【队列】 -> c
         */
        try{
            Connection connection = RabbitMQUtil.getConnection();
            Channel channel = connection.createChannel();
            // 推 push
//            channel.basicConsume(RabbitMQUtil.QUEUE_NAME_001, true, new DefaultConsumer(channel) {
//
//            })

            // 拉 pull
            channel.basicQos(1);
            channel.basicConsume(RabbitMQUtil.QUEUE_NAME_001, false, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    if (Objects.equals(properties.getDeliveryMode(), MessageProperties.PERSISTENT_TEXT_PLAIN.getDeliveryMode())) {
                        System.out.println("这是一条持久化消息");
                    }
                    Integer deliveryMode = properties.getDeliveryMode();
                    System.out.println("deliveryMode: " + deliveryMode);
                    String contentType = properties.getContentType();
                    System.out.println("contentType: " + contentType);
                    System.out.println("收到消息: " + new String(body));
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}