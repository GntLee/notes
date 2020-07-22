---------------------
bus					 |
---------------------
	# �ĵ�
		https://cloud.spring.io/spring-cloud-bus/spring-cloud-bus.html
	
	# ��Ҫ������MQ�м��
		RabbitMQ
		Kafka
	
	# ģ��
		* �ͻ������,bus����,���ӵ� mq
		* �������ݱ��޸ĺ�,��������ͻ��˵� /actuator/bus-refresh
		* ��ʱ,�������ӵ�bus�Ŀͻ��˶��ᷢ�����²���

	# �ͻ��� Maven,bus��ʵ��,��ѡһ
		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
        </dependency>
			* amqp����Rabbitmq
		
		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-kafka</artifactId>
        </dependency>
			* ʹ��Kafka
		
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
	
		* actuator ģ����������ṩ /actuator/bus-refresh ,bus-env �˵�,���ڴ�������ͻ��˵�ˢ��,�Ӷ�֪ͨ���������ӵ�Bus�Ŀͻ���
		* һ�����,����ִ��ˢ�²����Ŀͻ���һ�㶼�� : config-server
		* ������΢����ͻ��˲��سе�ˢ�����õĹ���,�Ӷ����˼�Ⱥ��һЩά������
		* ������΢����ͻ���Ҳ������Ҫ actuator ����

	# RabbitMQ�ͻ�������
spring:
  rabbitmq:
    host: 58.87.75.64
    port: 5671
    username: guest
    password: guest
    ssl:
      enabled: true


	# Kafka�ͻ�������
		TODO

	# ���𴥷�ˢ���¼��Ľڵ�,��Ҫ���� actuator,�ṩ /bus-refresh,bus-env �˵�
		management.endpoints.web.exposure.include=bus-refresh
		management.endpoints.web.exposure.include=bus-env
		
		* /actuator/bus-refresh
			* �������ø���(�ֶ��޸�������),�����bus�����б�ʶ�� @RefreshScope ������
			* @RefreshScope,���Ա�ʶ�ڷ���,�ֶ���,����һ�㶼��ͨ�� @Value ע��������ֵ
			* ��ע����һ������:proxyMode,�������ô���ģʽ(��̬���������ڶ�̬����)
				ScopedProxyMode proxyMode() default ScopedProxyMode.TARGET_CLASS;
				DEFAULT,
				NO,
				INTERFACES,
				TARGET_CLASS;


		* /actuator/bus-env
			* �޸�����,���Ҵ�������,�ö˵�ֱ���ṩ��ͨ��HTTP�޸�������,���ҹ㲥���µĹ���
			* POST����,������ΪJSON,����Ϊ
				{
					"name": "key1",
					"value": "value1"
				}
							
		

---------------------
������Ϣ�����¼�	 |
---------------------
	# ���ÿ���
		spring.cloud.bus.trace.enabled=true
	
	# ���ʶ˵�(actuator)
		/trace


---------------------
�ͻ��˼�����Ϣ�¼�	 |
---------------------
	# �̳���,����ʵ��
		RemoteApplicationEvent 
	
	# ע������ @RemoteApplicationEventScan
		* ָ��ʵ�����ɨ��� @RemoteApplicationEventScan(basePackages = {"com.acme", "foo.bar", "fizz.buzz"})
	