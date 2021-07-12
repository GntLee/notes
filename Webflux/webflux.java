-----------------
webflux			 |
-----------------
	# ��ַ
		https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html
		https://projectreactor.io/
	

-----------------
webflux	- ����
-----------------
	# Maven
		<parent>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-parent</artifactId>
			<version>2.3.3.RELEASE</version>
		</parent>
		<properties>
			<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
			<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
			<java.version>11</java.version>
			<skipTests>true</skipTests>
		</properties>

		<dependencies>
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-test</artifactId>
				<scope>test</scope>
			</dependency>
			<dependency>
				<groupId>io.projectreactor</groupId>
				<artifactId>reactor-test</artifactId>
				<scope>test</scope>
			</dependency>

			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-webflux</artifactId>
			</dependency>

		</dependencies>

		<build>
			<finalName>webflux</finalName>
			<plugins>
				<plugin>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-maven-plugin</artifactId>
					<configuration>
						<executable>true</executable>
						<includeSystemScope>true</includeSystemScope>
					</configuration>
				</plugin>
			</plugins>
		</build>
	

----------------------------------
webflux	- ��Ӧʽ��һЩ���ĸ���
----------------------------------
	# ���Ķ���
		* Mono/Flux
		* ������Ϊ��������������Mono����0����1�����ݣ�Flux����N������

	# 3���ź�
		����
		����(��ֹ����)
		���(��ֹ����)

	# ������
		map
			* ��ÿһ��Ԫ�ؽ���ӳ��, ���غ�����µ���

		flaMap
			* ��map����, ��ͬ���Ƕԡ����е�ÿ��Ԫ�ء�����ӳ�䣬���غ�����µ���



----------------------------------
webflux	- �������
----------------------------------
	DispatcherHandler
		* ǰ�˿�����

	HandlerMapping
		* ӳ��

	HandlerAdapter
		* ������ִ����

	HandlerResult
		* ���������

	HandlerResultHandler
		* �Դ�����������д���
	
	WebExceptionHandler
	WebFilter
	WebHandler
	WebSessionManager
	ServerCodecConfigurer
	LocaleContextResolver
	ForwardedHeaderTransformer


		
