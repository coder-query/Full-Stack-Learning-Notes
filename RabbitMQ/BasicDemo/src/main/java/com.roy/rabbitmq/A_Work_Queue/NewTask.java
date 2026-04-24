package com.roy.rabbitmq.A_Work_Queue;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.roy.rabbitmq.RabbitMQUtil;

import java.nio.charset.StandardCharsets;

public class NewTask {

	/**
	 * 发布一个task，交由多个Worker去处理。 每个task只要由一个Worker完成就行。
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Connection connection = RabbitMQUtil.getConnection();
		Channel channel = connection.createChannel();
        try {
            AMQP.Queue.DeclareOk queueDeclareOk = channel.queueDeclare(RabbitMQUtil.QUEUE_WORK, true, false, false, null);
            String queue = queueDeclareOk.getQueue();
            System.out.println("queue 的名字 ---> "+queue);
            for (int i = 0; i < 6; i++) {
                String message = "task " + i;
                channel.basicPublish("", RabbitMQUtil.QUEUE_WORK, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes(StandardCharsets.UTF_8));
				System.out.println(" [x] Sent '" + message + "'");
            }
        } finally {
			channel.close();
			connection.close();
        }
	}
}
