package com.roy.rabbitmq.exchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.roy.rabbitmq.RabbitMQUtil;

public class ReceiveLogsHeader {

	private static final String EXCHANGE_NAME = "logs";
	
	public static void main(String[] args) throws Exception {
		
		String routingKey= "ourTestRoutingKey";
		
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("x-match","all"); //x-match:特定的参数。all表示必须全部匹配才算成功。any表示只要匹配一个就算成功。
		headers.put("loglevel", "info");
		headers.put("buslevel", "product");
		headers.put("syslevel", "admin");

		Connection connection = RabbitMQUtil.getConnection();
		Channel channel = connection.createChannel();
		
	    channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.HEADERS);
	    String queueName = channel.queueDeclare("ReceiverHeader",true,false,false,null).getQueue();
	    
	    channel.queueBind(queueName, EXCHANGE_NAME,routingKey,headers);
	    
		Consumer myconsumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					BasicProperties properties, byte[] body)
					throws IOException {
				 System.out.println("========================");
				 String routingKey = envelope.getRoutingKey();
				 System.out.println("routingKey >"+routingKey);
				 String contentType = properties.getContentType();
				 System.out.println("contentType >"+contentType);
				 long deliveryTag = envelope.getDeliveryTag();
				 System.out.println("deliveryTag >"+deliveryTag);
				Map<String, Object> headerInfo = properties.getHeaders();
				headerInfo.forEach((key,value)-> System.out.println("header key: "+key+"; value: "+value));
				System.out.println("content:"+new String(body,"UTF-8"));
				 // (process the message components here ...)
				 //消费时自动应答了，这里就不能再重复应答。
//				 channel.basicAck(deliveryTag, false);
			}
		};
		
		String consumerTag = channel.basicConsume(queueName,true, myconsumer);
		System.out.println("consumerTag > "+consumerTag);
	}
}
