package com.roy.rabbitmq.D_Topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.roy.rabbitmq.RabbitMQUtil;

import java.nio.charset.StandardCharsets;

public class TopicProduct {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();

        // 申明一个Topic类型的交换机
        channel.exchangeDeclare(RabbitMQUtil.EXCHANGE_TOPIC_001, BuiltinExchangeType.TOPIC, true, false, null);

        // 申明两个队列
        channel.queueDeclare(RabbitMQUtil.QUEUE_TOPIC_001, true, false, false, null);
        channel.queueDeclare(RabbitMQUtil.QUEUE_TOPIC_002, true, false, false, null);

        // 绑定交换机和队列
        // # 表示匹配0个或多个字母
        // * 表示匹配一个字母
        channel.queueBind(RabbitMQUtil.QUEUE_TOPIC_001, RabbitMQUtil.EXCHANGE_TOPIC_001, RabbitMQUtil.TOPIC_001);
        channel.queueBind(RabbitMQUtil.QUEUE_TOPIC_002, RabbitMQUtil.EXCHANGE_TOPIC_001, RabbitMQUtil.TOPIC_002);
        for (int i = 0; i < 10; i++) {
            String message = "product topic " + RabbitMQUtil.TOPIC_001 + i;
            channel.basicPublish(RabbitMQUtil.EXCHANGE_TOPIC_001, "topic.ABCDEF", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
        for (int i = 0; i < 10; i++) {
            String message = "product topic " + RabbitMQUtil.TOPIC_002 + i;
            channel.basicPublish(RabbitMQUtil.EXCHANGE_TOPIC_001, "A.topic", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }
}
