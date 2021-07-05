---------------
������
---------------

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
	
	private static final String QUEUE_NAME = "simple_quque";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		
		// �������ӹ���
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("127.0.0.1");
		connectionFactory.setUsername("admin");
		connectionFactory.setPassword("123456");
		
		// ��ȡ����
		try (Connection connection = connectionFactory.newConnection()){
			
			// ����Channel
			try(Channel channel =  connection.createChannel()){
				
				// ��������
				DeclareOk declareOk = channel.queueDeclare(QUEUE_NAME, false, false, false, null);
				System.out.println(declareOk);
				
				String message = "������Ϣ!";
				
				channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
			}
		}
	}
}



---------------
������
---------------

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Consumer {
	
	private static final String QUEUE_NAME = "simple_quque";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		
		// �������ӹ���
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("127.0.0.1");
		connectionFactory.setUsername("admin");
		connectionFactory.setPassword("123456");
		
		// ����ʹ���� try (), ���Զ�����ִ����Ϻ��ͷ���Դ�ر�Channel, Connection
		// ����������ʱ�����û�ж�ȡ����Ϣ���ͻ��Զ��رգ������˳�
		// ����ȡ�� try()����ô�ͻ�����ֱ�����ѵ�����
		// ��Ȼ��Ҳ������while (true){}

		// ��ȡ����
		try (Connection connection = connectionFactory.newConnection()){  
			
			// ����Channel
			try(Channel channel =  connection.createChannel()){
				
				channel.basicConsume(QUEUE_NAME, true,(consumerTag, message) -> {
					byte[] body = message.getBody();
					System.out.println("�յ���Ϣ:" + new String(body, StandardCharsets.UTF_8));
				}, consumerTag -> {
					System.out.println("��Ϣ��ȡ��:" + consumerTag);
				});
				
			}
		}
	}
}
