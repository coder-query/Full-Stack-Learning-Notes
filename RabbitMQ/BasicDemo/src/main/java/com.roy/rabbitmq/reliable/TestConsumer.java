package com.roy.rabbitmq.reliable;

import com.rabbitmq.client.*;
import com.roy.rabbitmq.RabbitMQUtil;

import java.io.IOException;

/**
 * Author： roy
 * Description：
 **/
public class TestConsumer {

    private static final String EXCHANGE_NAME = "publisherExchange";
    public static final String QUEUE_NAME="publisherQueue";

    private static int offset = 0;
    public static void main(String[] args) throws Exception{
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME,"");


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
                if(offset++ % 2 == 0){
                    channel.basicAck(deliveryTag, true);
                }else{
                    channel.basicNack(deliveryTag, true, true);
                }
            }
        };
        channel.basicConsume(QUEUE_NAME, false,myconsumer);
    }
}
