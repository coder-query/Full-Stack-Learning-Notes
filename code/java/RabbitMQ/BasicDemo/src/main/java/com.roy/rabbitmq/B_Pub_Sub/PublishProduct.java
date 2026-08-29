package com.roy.rabbitmq.B_Pub_Sub;

import com.rabbitmq.client.*;
import com.roy.rabbitmq.RabbitMQUtil;

import java.nio.charset.StandardCharsets;

public class PublishProduct {
    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        // 申明一个Fanout类型的交换机
        channel.exchangeDeclare(
                RabbitMQUtil.EXCHANGE_FANOUT_001,
                BuiltinExchangeType.FANOUT,
                true,
                false,
                null
        );
        // 申明两个队列
        channel.queueDeclare(RabbitMQUtil.QUEUE_FANOUT_001, true, false, false, null);
        channel.queueDeclare(RabbitMQUtil.QUEUE_FANOUT_002, true, false, false, null);

        // 绑定交换机和队列
        channel.queueBind(RabbitMQUtil.QUEUE_FANOUT_001, RabbitMQUtil.EXCHANGE_FANOUT_001, "");
        channel.queueBind(RabbitMQUtil.QUEUE_FANOUT_002, RabbitMQUtil.EXCHANGE_FANOUT_001, "");

        // 发送广播消息
        for (int i = 0; i < 10; i++){
            String message = "product " + i;
            channel.basicPublish(RabbitMQUtil.EXCHANGE_FANOUT_001, "", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes(StandardCharsets.UTF_8));
			System.out.println(" [x] Sent '" + message + "'");
        }

        channel.close();
		connection.close();
    }
}
