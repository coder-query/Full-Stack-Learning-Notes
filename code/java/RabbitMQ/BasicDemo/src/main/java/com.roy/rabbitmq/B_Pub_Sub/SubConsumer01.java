package com.roy.rabbitmq.B_Pub_Sub;

import com.rabbitmq.client.*;
import com.roy.rabbitmq.RabbitMQUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SubConsumer01{
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RabbitMQUtil.QUEUE_FANOUT_001, true, false, false, null);
        channel.basicConsume(RabbitMQUtil.QUEUE_FANOUT_001, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    System.out.println("========================");
                    String routingKey = envelope.getRoutingKey();
                    System.out.println("routingKey >" + routingKey);
                    String contentType = properties.getContentType();
                    System.out.println("contentType >" + contentType);
                    long deliveryTag = envelope.getDeliveryTag();
                    System.out.println("deliveryTag >" + deliveryTag);
                    System.out.println("content:" + new String(body, StandardCharsets.UTF_8));
                    channel.basicAck(envelope.getDeliveryTag(), false);  // 手动确认
                } catch (Exception e) {
                    e.printStackTrace();
                    // 拒绝消息，可以选择重新入队
                    channel.basicNack(envelope.getDeliveryTag(), false, true);
                }
            }
        });
    }
}
