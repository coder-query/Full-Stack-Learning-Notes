package com.roy.rabbitmq.zzbasic;

import com.rabbitmq.client.*;
import com.roy.rabbitmq.RabbitMQUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @auth roykingw
 */
public class CallbackConsumer {
    private static final String EXCHANGE_NAME="callbackExchange";
    private static final String ALTER_EXCHANGE_NAME="alterExchange";
    private static final String QUEUE_NAME = "callbackQueue";

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        Map<String,Object> params = new HashMap<>();
        params.put("alternate-exchange",ALTER_EXCHANGE_NAME);
        channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.DIRECT,true,false,params);
        channel.exchangeDeclare(ALTER_EXCHANGE_NAME,BuiltinExchangeType.DIRECT,true,false,null);

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"key1");

        channel.basicConsume(QUEUE_NAME, new DeliverCallback() {
                    @Override
                    public void handle(String consumerTag, Delivery message) throws IOException {
                        long deliveryTag = message.getEnvelope().getDeliveryTag();
                        String correlationId = message.getProperties().getCorrelationId();
                        System.out.println("received message consumerTag: " + consumerTag + "; message: " + new String(message.getBody())+";deliveryTag: "+deliveryTag+";correlationId: "+correlationId);
                        channel.basicAck(deliveryTag,false);
                    }
                }
                //去控制台把队列删掉就会触发cancel
                , new CancelCallback() {
                    @Override
                    public void handle(String consumerTag) throws IOException {
                        System.out.println("canceled message consumerTag: " + consumerTag + "; ");
                    }
                }
                , new ConsumerShutdownSignalCallback() {
                    @Override
                    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
                        System.out.println("consumer shutdown message consumerTag: " + consumerTag + "; Exception: " + sig);
                    }
                });
    }
}
