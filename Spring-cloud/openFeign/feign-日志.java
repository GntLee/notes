---------------------
Feign ��־
---------------------
	# ����ͨ������Ϊfeign�����־��¼��
		

	# ����DEBUG��־��Ĭ�ϵ���־��¼�����ƾ���: �ͻ��˵Ľӿڵ���������
		logging.level.com.demo.user.client.OrderClient=DEBUG
		
		
		* ֻ���� DEBUG ����feign�Ż������־��Ϣ
		*  com.demo.user.client.OrderClient ���ǽӿ�����
	
	# ͨ������������־�����ѡ��

		import feign.Logger;
		import org.springframework.context.annotation.Bean;
		import org.springframework.context.annotation.Configuration;

		@Configuration
		public class FeignConfiguration {

			// ��־���������
			@Bean
			public Logger.Level feignLoggerLevel() {
				return Logger.Level.FULL;
			}
		}

		* ��־�������ö�ٿ�ѡֵ
			NONE	����־��¼��Ĭ�ϣ���
			BASIC	ֻ��¼���󷽷��� URL �Լ���Ӧ״̬���ִ��ʱ�䡣
			HEADERS	��¼������Ϣ�Լ��������Ӧͷ��
			FULL	��¼�������Ӧ�ı�ͷ�����ĺ�Ԫ��
					

		* �������־
			2021-07-10 16:01:32.635 DEBUG 14416 --- [pool-1-thread-1] com.demo.user.client.OrderClient         : [OrderClient#demo] ---> GET http://order-service/demo HTTP/1.1
			2021-07-10 16:01:32.636 DEBUG 14416 --- [pool-1-thread-1] com.demo.user.client.OrderClient         : [OrderClient#demo] ---> END HTTP (0-byte body)
			2021-07-10 16:01:32.780 DEBUG 14416 --- [pool-1-thread-1] com.demo.user.client.OrderClient         : [OrderClient#demo] <--- HTTP/1.1 200 OK (141ms)
			2021-07-10 16:01:32.780 DEBUG 14416 --- [pool-1-thread-1] com.demo.user.client.OrderClient         : [OrderClient#demo] connection: keep-alive
			2021-07-10 16:01:32.780 DEBUG 14416 --- [pool-1-thread-1] com.demo.user.client.OrderClient         : [OrderClient#demo] content-length: 7
			2021-07-10 16:01:32.780 DEBUG 14416 --- [pool-1-thread-1] com.demo.user.client.OrderClient         : [OrderClient#demo] content-type: text/plain;charset=utf-8
			2021-07-10 16:01:32.780 DEBUG 14416 --- [pool-1-thread-1] com.demo.user.client.OrderClient         : [OrderClient#demo] date: Sat, 10 Jul 2021 08:01:32 GMT
			2021-07-10 16:01:32.780 DEBUG 14416 --- [pool-1-thread-1] com.demo.user.client.OrderClient         : [OrderClient#demo] 
			2021-07-10 16:01:32.781 DEBUG 14416 --- [pool-1-thread-1] com.demo.user.client.OrderClient         : [OrderClient#demo] success
			2021-07-10 16:01:32.781 DEBUG 14416 --- [pool-1-thread-1] com.demo.user.client.OrderClient         : [OrderClient#demo] <--- END HTTP (7-byte body)