package com.roy.rabbitmq.transaction;

import com.rabbitmq.client.*;
import com.roy.rabbitmq.RabbitMQUtil;

/**
 * @auth roykingw
 */
public class TransactionProducer {

    private static final String QUEUE_NAME="transactionQueue";

    public static void main(String[] args) throws Exception{
        Connection connection = RabbitMQUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        AMQP.Tx.SelectOk selectOk = channel.txSelect();
        for(int i = 0 ; i < 10000 ; i ++){
            String message = "transaction message "+i;
            try{
                channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
                if(i == 2000){
                    System.out.println("rollback");
                    channel.txRollback();
                    break;
                }
            }catch (Exception e){
                channel.txRollback();
            }
        }
        channel.txCommit();
        channel.close();
        connection.close();
    }
}
