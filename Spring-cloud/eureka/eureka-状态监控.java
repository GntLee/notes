------------------------
ʵ����ؽӿ�			|
------------------------
	# �ýӿڻ᷵��JSON��ʽ��ʵ��״̬��Ϣ
	# ��Ҫ�������
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		
		* �������,spring-boot��һ�����������������ļ�����

	# ������
		eureka.instance.status-page-url=${management.context-path}/info
			# ʵ��״̬ҳ���ַ

		eureka.instance.health-check-url=${management.context-path}/health
			# ����״��ָʾ����ַ