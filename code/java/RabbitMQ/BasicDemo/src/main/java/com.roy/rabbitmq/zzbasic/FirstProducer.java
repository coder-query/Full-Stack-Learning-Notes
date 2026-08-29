package com.roy.rabbitmq.zzbasic;

import com.rabbitmq.client.*;
import com.roy.rabbitmq.RabbitMQUtil;

import java.nio.charset.StandardCharsets;

/**
 * @auth roykingw
 */
public class FirstProducer {



    public static void main(String[] args) throws Exception{
            try(Connection connection = RabbitMQUtil.getConnection();
                Channel channel = connection.createChannel();) {

                /**
                 * 1. 基础的模型 (申明队列) p -> 【队列】 -> c
                 */
                /**
                 * 声明一个对列。几个参数依次为： 队列名，durable是否实例化；exclusive：是否独占；autoDelete：是否自动删除；arguments:参数
                 * 这几个参数跟创建队列的页面是一致的。
                 * 如果Broker上没有队列，那么就会自动创建队列。
                 * 但是如果Broker上已经由了这个队列。那么队列的属性必须匹配，否则会报错。
                 */
                AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(
                        RabbitMQUtil.QUEUE_NAME_001,  // 队列名
                        true,            // durable 持久化
                        false,           // exclusive 非独占(如果是true，则其他连接不能创建同名的队列，且队列在这个channel连接断开时自动删除)
                        false,           // autoDelete 不自动删除
                        null             // 参数
                );
                System.out.println("队列名: " + declareOk.getQueue());

                // 2. 发送一条消息（这样界面上能看到队列里有消息）
                String message = "hello world 6666";
                channel.basicPublish(
                        "",
                        RabbitMQUtil.QUEUE_NAME_001,
                        MessageProperties.PERSISTENT_TEXT_PLAIN,
                        message.getBytes(StandardCharsets.UTF_8)
                );
                System.out.println("发送了消息: " + message);
            }
    }
}
