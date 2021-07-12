----------------------------
Gateway
----------------------------
	# ��ַ
		https://github.com/spring-cloud/spring-cloud-gateway
		https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/

	
	# Maven
		<!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-gateway -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-gateway</artifactId>
			<version>3.0.3</version>
		</dependency>
		
		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-loadbalancer</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>
        <dependency>
            <groupId>com.github.ben-manes.caffeine</groupId>
            <artifactId>caffeine</artifactId>
        </dependency>
		
		* ��ʹ��Nacos��ʱ���������� openfeign��loadbalancer ���ܻᵼ�·��� 503 ������

----------------------------
���ĸ���
----------------------------
	# Router
		* ����: ID, Ŀ��URI, ν�ʼ���, ���������� ���
		* ����ۺ�ν��Ϊ�棬��ƥ��·��
	
	# ν��
		* Predicate �����ж��Ƿ�ִ�е�ǰ·��
	
	# ������
		* ��ִ��·�ɵ�ʱ�򣬻�ִ�еĹ��˲���


----------------------------
Gateway
----------------------------
	# ��Router�е�URI����Ϊ���񣬲��ҿ������ؾ���
		lb://<service-name>
	





----------------------------
���õ�����
----------------------------
spring:
  cloud:
    gateway:
      routes:
        - id: user_router
          uri: lb://user-service
          predicates:
            - Path=/api/user/**
          filters:
            - RewritePath=/api/user/?(?<segment>.*), /$\{segment}
            - name: RequestSize
              args:
                maxSize: 10KB
