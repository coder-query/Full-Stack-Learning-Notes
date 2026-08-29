package com.roy.rabbitmq.sharding;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author roy
 * @desc
 */
public class ShardingProducer {
    private static final String EXCHANGE_NAME = "sharding_exchange";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.65.193");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("/mirror");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //发送者只管往exchange里发消息，而不用关心具体发到哪些queue里。
        channel.exchangeDeclare(EXCHANGE_NAME, "x-modulus-hash");
//        AMQP.Tx.SelectOk selectOk = channel.txSelect();
        for(int i = 0 ; i < 3000 ; i ++){
            String message = "Sharding message "+i;
            try{
                channel.basicPublish(EXCHANGE_NAME, String.valueOf(i), null, message.getBytes());
                //测试消息回滚
//                if(i == 2000){
//                    System.out.println("rollback");
//                    channel.txRollback();
//                    break;
//                }
            }catch (Exception e){
                channel.txRollback();
            }
        }
//        channel.txCommit();
        channel.close();
        connection.close();
    }
}
