package com.roy.rabbitmq.reliable;

import com.rabbitmq.client.*;
import com.roy.rabbitmq.RabbitMQUtil;

import java.io.IOException;
import java.time.Duration;

/**
 * 模拟PublisherConfirm机制的作用
 */
public class PublishConfirmTest {

	private static final String EXCHANGE_NAME = "publisherExchange";
	/**
	 * exchange有四种类型， fanout topic headers direct
	 * direct类型的exchange会根据routingkey，将消息转发到该exchange上绑定了该routingkey的所有queue
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		Connection connection = RabbitMQUtil.getConnection();
		Channel channel = connection.createChannel();
		//发送者只管往exchange里发消息，而不用关心具体发到哪些queue里。
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

		channel.confirmSelect();
		long start = System.nanoTime();
		channel.addConfirmListener(
				//消息发送成功的回调
				new ConfirmCallback() {
				   @Override
				   public void handle(long deliveryTag, boolean multiple) throws IOException {
					   System.out.println("message send successed , delivery Tag = "+deliveryTag );
				   }
			   }
			   //Broker因为某些原因无法保存消息时 会发起回调。
				, new ConfirmCallback() {
					@Override
					public void handle(long deliveryTag, boolean multiple) throws IOException {
						System.out.println("message send failed , delivery Tag = "+deliveryTag );
					}
				});
		//消息路由不到队列时的监听。
		channel.addReturnListener(new ReturnCallback() {
			@Override
			public void handle(Return returnMessage) {
				System.out.println("message returned : "+new String(returnMessage.getBody()));
			}
		});
		/*
			Step1:在发送消息前打个断点。 然后去RabbitMQ上把Exchange手动删除掉。
		 */
		for(int i = 0 ; i < 10 ; i ++){
			String message = "Publisher Confirms "+ i;
			channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
			/*
				step2: 这时basicPublish发送消息时是不会报错的。
					在waitForConfirmsOrDie时就会发现连接已经关闭的异常。
			 */
			try{
				channel.waitForConfirmsOrDie(1000);
			}catch (Exception e){
				System.out.println("message send error; i = "+i);
				e.printStackTrace();
			}
		}
		long end = System.nanoTime();
		System.out.format("Published %,d messages individually in %,d ms%n", 10, Duration.ofNanos(end - start).toMillis());
		/*
			如果没有通过waitForConfirmsOrDie感知异常，那么关闭连接的异常会在连接关闭时才抛出来。
			这时的异常感知是不及时的，消息会被认为已经成功发送出去了。
			这样就能体现出PublisherConfirm机制的作用。
		 */

		Thread.sleep(10000);
		channel.close();
		connection.close();
	}
}
