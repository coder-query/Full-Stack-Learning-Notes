package com.roy.rabbitmq.basic;

import com.rabbitmq.client.*;
import com.roy.rabbitmq.RabbitMQUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
//        try{
//            Connection connection = RabbitMQUtil.getConnection();
//            Channel channel = connection.createChannel();
//            // 推 push
//            channel.basicQos(1);
//            channel.basicConsume(RabbitMQUtil.QUEUE_NAME_001, false, new DefaultConsumer(channel) {
//                @Override
//                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                    if (Objects.equals(properties.getDeliveryMode(), MessageProperties.PERSISTENT_TEXT_PLAIN.getDeliveryMode())) {
//                        System.out.println("这是一条持久化消息");
//                    }
//                    Integer deliveryMode = properties.getDeliveryMode();
//                    System.out.println("deliveryMode: " + deliveryMode);
//                    String contentType = properties.getContentType();
//                    long deliveryTag = envelope.getDeliveryTag();
//                    System.out.println("deliveryTag: " + deliveryTag);
//                    String routingKey = envelope.getRoutingKey();
//                    System.out.println("routingKey: " + routingKey);
//                    String exchange = envelope.getExchange();
//                    System.out.println("exchange: " + exchange);
//                    System.out.println("contentType: " + contentType);
//                    System.out.println("收到消息: " + new String(body));
//                    channel.basicAck(deliveryTag, false);
//                }
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        Connection connection = null;
        Channel channel = null;
        try {
            connection = RabbitMQUtil.getConnection();
            channel = connection.createChannel();

            // Pull 模式：主动拉取一条消息
            channel.basicQos(1);
            GetResponse response = channel.basicGet(RabbitMQUtil.QUEUE_NAME_001, false);
            if (response != null) {
                String message = new String(response.getBody(), StandardCharsets.UTF_8);
                System.out.println("收到消息: " + message);
                channel.basicAck(response.getEnvelope().getDeliveryTag(), false);
            } else {
                System.out.println("队列中没有消息");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 先关 Channel，再关 Connection
            if (channel != null) {
                try { channel.close(); } catch (Exception e) { e.printStackTrace(); }
            }
            if (connection != null) {
                try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
            }
        }

    }
}