----------------------------
ConfigServer ��Ⱥ			|
----------------------------
	# ���÷������߿���

	# ��ͳģʽ
		* ����ģʽ,���ǰ����е����ö�����һ��Git�ֿ�
		* Ȼ��ʹ�ö�̨���÷�����,��Ӧ�ò㸺�ؾ���

	# ����ģʽ
		* �����÷�������Ϊ����,ע�ᵽEureka
		* �ͻ���ͨ��Eureka��ȡ��ע������÷���

----------------------------
ConfigServer ����			|
----------------------------
	# ��������Eureka�Ŀͻ�������
		 <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
	
	# �����ע�ᵽע������
		eureka:
		  client:
		    # ���÷���������Ҫ��ע�����ļ�������
			fetch-registry: false
			# ��Ҫ���Լ�ע�ᵽע������
			register-with-eureka: true
			service-url:
			  defaultZone: http://username:password@localhost:8081/eureka/
	
	# �ͻ������Eureka����(��Ϊ΢����������,�϶��Ѿ�����)
		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

	# �ͻ�������
		* ʹ��������,����ԭ����uri����
		* һ���������÷������ķ���,һ���������÷�������id
			spring.cloud.config.discovery.enabled
			spring.cloud.config.discovery.service-id

		* ��Ҫ�� eureka��config�����ö�һͬ��д�� : boostrap.yml ������
			spring:
			  application:
				name: springcloud
			  # ���eureka�����˻������뱣���Ļ�
			  security:
				user:
				  name: springcloud
				  password: 123456
			  cloud:
				config:
				  # ���������ֻ���
				  discovery:
					# ���÷�������id
					service-id: CONFIG-SERVER
					enabled: true
				  profile: test
				  label: master

			eureka:
			  client:
				service-url:
				  defaultZone: http://${spring.security.user.name}:${spring.security.user.password}@localhost:8081/eureka/
				register-with-eureka: false
