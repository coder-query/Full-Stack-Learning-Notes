package com.roy.rabbitmq.basic;

import com.rabbitmq.client.*;
import com.roy.rabbitmq.RabbitMQUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @auth roykingw
 * 1、ReturnListener: 如果发往Exchange的消息无法路由，就会给Producer一个通知，触发ReturnListener。
 * 2、可以给Exchange添加一个alternate-exchange参数。表示发往当前的Exchange上的消息如果无法路由到队列，就转到到另一个交换机上。
 */
public class CallbackProducer {

    private static final String EXCHANGE_NAME="callbackExchange";
    private static final String ALTER_EXCHANGE_NAME="alterExchange";
    private static final String QUEUE_NAME = "callbackQueue";

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        //给交换机添加一个备用交换机，如果发往EXCHANGE_NAME的消息无法路由，就会转发到ALTER_EXCHANGE_NAME上。
        Map<String,Object> params = new HashMap<>();
        params.put("alternate-exchange",ALTER_EXCHANGE_NAME);
        channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.DIRECT,true,false,params);
        channel.exchangeDeclare(ALTER_EXCHANGE_NAME,BuiltinExchangeType.DIRECT,true,false,null);

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"key1");
        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("message acked; deliveryTag = " +deliveryTag);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("message Nacked; deliveryTag = " +deliveryTag);
            }
        });
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey,
                                     AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("message returned replayCode = "+replyCode+";replyText:"+replyText
                + "; message = "+new String(body)+";props = "+properties);
            }
        });
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        builder.deliveryMode(MessageProperties.PERSISTENT_TEXT_PLAIN.getDeliveryMode());
        builder.priority(MessageProperties.PERSISTENT_TEXT_PLAIN.getPriority());
        builder.correlationId("111");

        String message = "correlationId=1,asdfasdfas";
        //发送两条消息， routingkey为key2的消息没有队列接收，就会触发return callback
        channel.basicPublish(EXCHANGE_NAME,"key1",true, builder.build(),message.getBytes(StandardCharsets.UTF_8));
        channel.basicPublish(EXCHANGE_NAME,"key2",true, builder.build(),message.getBytes(StandardCharsets.UTF_8));
        System.out.println("message sended");
        //不要立即结束，这样才能接收到服务端的回调。
        Thread.sleep(10000);
        channel.close();
        connection.close();
    }
}
