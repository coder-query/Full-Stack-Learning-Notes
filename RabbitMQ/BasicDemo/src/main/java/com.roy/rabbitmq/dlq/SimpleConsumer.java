package com.roy.rabbitmq.dlq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @auth roykingw
 */
public class SimpleConsumer {

    private static final String HOST_NAME="192.168.65.193";
    private static final int HOST_PORT=5672;

    private static final String EXCHANGE_NAME="testdlq.exchange";
    private static final String QUEUE_NAME="testdlq";
    public static final String USER_NAME="admin";
    public static final String PASSWORD="admin";
    public static final String VIRTUAL_HOST="/mirror";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST_NAME);
        factory.setPort(HOST_PORT);
        factory.setUsername(USER_NAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost(VIRTUAL_HOST);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout", true, false, null);
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        //回调函数，处理接收到的消息
        Consumer myconsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                System.out.println("========================");
                String routingKey = envelope.getRoutingKey();
                System.out.println("routingKey >"+routingKey);
                String contentType = properties.getContentType();
                System.out.println("contentType >"+contentType);
                long deliveryTag = envelope.getDeliveryTag();
                System.out.println("deliveryTag >"+deliveryTag);
                System.out.println("content:"+new String(body,"UTF-8"));
                // (process the message components here ...)
                channel.basicAck(deliveryTag, false);
            }
        };
        //从test1队列接收消息
        channel.basicConsume(QUEUE_NAME, false,myconsumer);
    }
}
