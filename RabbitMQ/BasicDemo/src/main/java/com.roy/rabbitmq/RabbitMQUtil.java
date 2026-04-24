package com.roy.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQUtil {

	// 单例模式
	private static volatile Connection connection;
	private static volatile ConnectionFactory connectionFactory;

	//
	public static final String HOST_NAME="192.168.211.102";
	public static final int HOST_PORT=5672;
	public static final String USER_NAME="rabbitmq";
	public static final String PASSWORD="rabbitmq";
	public static final String VIRTUAL_HOST="/";
	public static final String QUEUE_WORK="work";
	public static final String QUEUE_NAME_001="myQueue_001";
	public static final String EXCHANGE_NAME="callbackExchange";
	public static final String EXCHANGE_FANOUT_001="exchange.fanout.001";
	public static final String QUEUE_FANOUT_001="queue.fanout.001";
	public static final String QUEUE_FANOUT_002="queue.fanout.002";
	public static final String EXCHANGE_HEADERS_001="exchange.headers.001";
	public static final String EXCHANGE_DIRECT_001="exchange.direct.001";
	public static final String QUEUE_DIRECT_001="queue.direct.001";
	public static final String QUEUE_DIRECT_002="queue.direct.002";
	public static final String ROUTING_001="routing.001";
	public static final String ROUTING_002="routing.002";
	public static final String EXCHANGE_TOPIC_001="exchange.topic.001";
	public static final String QUEUE_TOPIC_001="queue.topic.001";
	public static final String QUEUE_TOPIC_002="queue.topic.002";
	public static final String TOPIC_001 ="topic.#";
	public static final String TOPIC_002 ="*.topic";
	
	private RabbitMQUtil() {
	}

	/**
	 * 保证ConnectionFactory单例
	 * @return
	 */
	public static ConnectionFactory getConnectionFactory() {
		if (null == connectionFactory){
			synchronized (RabbitMQUtil.class){
				if (null == connectionFactory){
					connectionFactory = new ConnectionFactory();
					connectionFactory.setHost(HOST_NAME);
					connectionFactory.setPort(HOST_PORT);
					connectionFactory.setUsername(USER_NAME);
					connectionFactory.setPassword(PASSWORD);
					connectionFactory.setVirtualHost(VIRTUAL_HOST);
				}
			}
		}
		return connectionFactory;
	}

	/**
	 * 保证Connection单例
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		if (null == connection){
			synchronized (RabbitMQUtil.class){
				if (null == connection){
					connection = getConnectionFactory().newConnection();
				}
			}
		}
		return connection;
	}
}
