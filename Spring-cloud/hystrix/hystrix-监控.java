----------------------------
hystrix ���				|
----------------------------
	# Hystrix �ṩ��ʵʱ���ü��:Hystrix Dashboard
	# ���Գ����ļ�¼����ͨ�� Hystrix���������ִ����Ϣ,������ͳ�Ʊ����ͼ�ε���ʽչʾ���û�
		* ����ÿ��ִ�ж��ٴ�����,���ٴ�����ִ�гɹ�,���ٴ�ʧ��
	
	# maven����
		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
        </dependency>
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
	
	# ���ע��
		@EnableHystrixDashboard
	
	# ����
		${ctx}/hystrix
	
