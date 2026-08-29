package com.roy.rabbitmq.C_Routing;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.roy.rabbitmq.RabbitMQUtil;

import java.nio.charset.StandardCharsets;

public class RoutingProduct {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(RabbitMQUtil.EXCHANGE_DIRECT_001, BuiltinExchangeType.DIRECT, true, false, null);
        channel.queueDeclare(RabbitMQUtil.QUEUE_DIRECT_001, true, false, false, null);
        channel.queueDeclare(RabbitMQUtil.QUEUE_DIRECT_002, true, false, false, null);
        channel.queueBind(RabbitMQUtil.QUEUE_DIRECT_001, RabbitMQUtil.EXCHANGE_DIRECT_001, RabbitMQUtil.ROUTING_001);
        channel.queueBind(RabbitMQUtil.QUEUE_DIRECT_002, RabbitMQUtil.EXCHANGE_DIRECT_001, RabbitMQUtil.ROUTING_002);
        for (int i = 0; i < 10; i++) {
            String message = "product routing " + RabbitMQUtil.ROUTING_001 + i;
            channel.basicPublish(RabbitMQUtil.EXCHANGE_DIRECT_001, RabbitMQUtil.ROUTING_001, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
        for (int i = 0; i < 10; i++) {
            String message = "product routing " + RabbitMQUtil.ROUTING_002 + i;
            channel.basicPublish(RabbitMQUtil.EXCHANGE_DIRECT_001, RabbitMQUtil.ROUTING_002, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }
}
