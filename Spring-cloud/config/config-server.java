--------------------------------
ConfigServer					|
--------------------------------
	# Maven
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-config-server</artifactId>
			</dependency>
	
	# Ĭ��ʹ��Git�ֿ���Ϊ���òֿ�
		* �����ļ������Ѿ����汾����������
		* spring.config.server.git.uri ��ϵĸ�·��,����Ҫ�� .git �ļ���(�������õ���Ŀ¼)
		* ���������ļ���Ҫ�ύ���汾����
	
	# ע������
		@EnableConfigServer
	
	# ����������
		server:
		  port: 8015

		spring:
		  application:
			name: config-server
		  cloud:
			config:
			  server:
				git:
				  # git���òֿ��λ��(������Ϊ / ��β),��Ŀ¼�±���Ҫ��: .git Ŀ¼(˵����,������GIT�ĸ�Ŀ¼)
				  uri: https://github.com/KevinBlandy/temp-config.git
				  # ���ػ����Ŀ¼
				  basedir: D:\\temp\\config
				  # ���òֿ��µ��������·��,�����ж��
				  search-paths:
					- config
				  # ���ʲֿ���˻�������
				  username: KevinBlandy
				  password: F8575532
	
	# ��������OK��,����ͨ�����������
		http://localhost:8015/config/springcloud.yml

		* ���Է��ʵ�·��
			/{application}/{profile}/{label}

			/{application}-{profile}.yml
			/{label}/{application}-{profile}.yml

			/{application}-{profile}.properties
			/{label}/{application}-{profile}.properties
			
			application
				* Ҫ�Կͻ��˵� spring.application.name ������
			profile
				* ����
			label
				* ����ָ����֧,Ĭ��ΪMASTER
		
		* ע��,Ҫ���� search-paths ·��
	
	# ConifgServer�����ڱ��ػ��������ļ�
		* ���ػ����Ŀ¼
			C:\Users\KevinBlandy\AppData\Local\Temp\config-repo-[�����]
		
		* ��ֹGit������崻����޷���������
		* ����ͨ��������ָ������Ļ���Ŀ¼
			spring.cloud.config.server.git.basedir=D:\\temp\\config
	
	
	
	# ����ʹ�ñ��ص� git �ֿ���Ϊ���òֿ�
		spring.config.server.git.uri=file:D:\\config-rep\\springcloud-config

		* ʹ��:file: ��ͷ��ʾʹ�ñ��ص����òֿ�
	
	# ʹ��URIռλ�������ֲ�ͬ��Ӧ��
		* application,profile,label ���������Ա�ʶ�����ļ�����

		* ����������ConfigServer����Git�ֿ��Uri��ַ
			spring.config.server.git.uri=https://github.com/KevinBlandy/{application}-config.git
			
			application
				* ��ʾӦ����,���ͻ��˷��������ʱ��,Server����ݿͻ��˵� spring.application.name ��Ϣ�����URI
				* �Ϳ��Ը��ݲ�ͬ�ķ���,����̬�ļ��ز�ͬURI�µ���Դ
			
			label
				* ��������Ƚ��ر�,���Git��֧���ư����� '/' ,��ô��label������http��uri��Ӧ��ʹ�� '_' ������,����ı���URI�ĺ���

			* Ŀǰ����,����ռλ��{application}��֧�������� uri ������,���ʹ�� {application},��ConfigServer������ʱ��ᱻ�滻Ϊ : app,�Ӷ�����ϵͳ�쳣,��ʾ�����ڵ�Ŀ¼
				���� -> uri: 'https://github.com/KevinBlandy/{application}-config-rep.git'
				�쳣 -> Cannot clone or checkout repository: https://github.com/KevinBlandy/app-config-rep.git

			* ��ʵҲ���Խ��,�ײ�,��ConfigServer������ʱ��,���ڸ�·������Ŀ¼: {application},application�滻Ϊ app,���ҳ�ʼ��ΪgitĿ¼,������commit�ļ�
				���� -> uri: 'file:D:\\config-rep\\{application}-config'
				�½� -> D:\\config-rep\\app-config
			
			* ��������������Ϊ������⵼�µ�
		
		* ռλ��������ʹ��������·����,�Դ�������ͬһ���ֿ��µĲ�ͬ�ļ���
			spring
			  cloud:
			    config:
			      server:
				    # Git�ĸ�Ŀ¼
				    uri: 'file:D:\\config-rep'
				    search-paths:
					  # �����yml�Ļ�,Ҫʹ��˫����
					  - '{application}-config'

			* ���ݲ���Ӧ����,ȥ��ͬ����Ŀ¼������
			* �������,��������ûɶ����

		
	
	# ��ֿ������
		spring.cloud.config.server.git.uri='file:\\default'
		spring.cloud.config.server.git.repos.dev.pattern='dev/*'
		spring.cloud.config.server.git.repos.dev.uri='file:D:\\dev'

		spring.cloud.config.server.git.repos.test.pattern='test/*'
		spring.cloud.config.server.git.repos.test.uri='file:D:test'

		* git.uri ָ����Ĭ�ϵĲֿ�,ϵͳ�����ͻ�ȥ����
		
		* ָ���������ƵĲֿ�,�Լ����ʵ�pattern
			git.repos.<name>.pattern
			git.repos.<name>.uri

--------------------------------
����Ȩ��						|
--------------------------------
	# Ĭ�ϲ���HTTPЭ��ļ�Ȩ
		spring.cloud.config.server.git.username
		spring.cloud.config.server.git.password
	
	# ����SSHЭ��ļ�Ȩ
		��Ҫ�Լ��ڵ�ǰ������������ ssh��Կ��,���Ұѹ�Կ���õ�git�ķ�����

--------------------------------
�����ļ�ϵͳ					|
--------------------------------
	# ��ʹ��GIT/SVN,ֱ��ʹ���ļ�ϵͳ
		spring.profiles.active=native

		cloud.config.server.native.search-locations
			* ָ��һ�����߶�������ļ�������·��

		* ������ʹ�ñ����ļ�ϵͳ,����Ҫʹ��GIT

--------------------------------
�������						|
--------------------------------
	# �� gti.uri ������ʹ�� {application} ռλ��,���������,(�Ҳ��� app �ֿ�)��ΪServerʵ���˽��������
		ConfigServerHealthIndicator

		* �ڸü������Ĭ�Ϲ���һ�� application Ϊ app �Ĳֿ�

	
	# ����ֱ��Git�Ĳֿ����½�һ�����ÿ�,�ý������������
		spring
		  cloud:
			config:
			  server:
				health:
				  # ����ѡ��رս������
				  enabled: false
				  # Map<String, Repository> repositories
				  repositories:
					check:
					  name: check-rep
					  label: master
					  profiles: default
		

--------------------------------
���Ը���						|
--------------------------------
	# ͨ�����������õ�K/V���ᱻ���ص��ͻ���,���ǿͻ��˲����޸�
		spring:
		  cloud:
			config:
			  server:
			    # Map<String, String>
				overrides:
				  name: KevinBlandy
				  skill:
					- Java
					- Python

--------------------------------
��ȫ����						|
--------------------------------
	# ��������Security����
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
	
	# ����������û���������
		spring:
		  security:
			user:
			  # ����˵��˻�������
			  name: config
			  password: 123456
		
		* ����˷��ʶ˵�,Ҳ��Ҫʹ�ø��˻��������¼
	
	# �ͻ���ʹ���û�������������
		spring:
		  cloud:
			config:
			  # ���ʷ���˵��˻�������
			  username: config
			  password: 123456
	
--------------------------------
���õļ���/����					|
--------------------------------
	# ��SpringCloud Config �м��ص�����ʹ�� {cipher} ǰ׺����ʶ,��ʾ��������һ�����ܵ�����ֵ

		spring.datasource.password={cipher}e10adc3949ba59abbe56e057f20f883e

		* ���÷��������м��ص�ʱ��,���Զ�Ϊ���� {cipher} ǰ׺�����ý��н���
		* ʹ�� yml ����,��Ҫʹ�� '' ����ֵ
		
	# ʹ�õ�ǰ��,��Ҫ��Oracle�ٷ���������:jce
		local_policy.jar
		US_export_policy.jar
	
		* Java8�����ص�ַ:https://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
		* ��ӵ�: $JAVA_HOME/jre/lib/security Ŀ¼��
	
	# ����������Կ����(�ԳƼ���),��Ҫ������:bootstrap.yml
		encrypt:
		  key: 4D44331C666011E9B03100163E11BA6D
	
	# ���Է��ʵĶ˵�
		/encrypt/status	���ܹ��ܵ�״̬
		/key			�鿴��Կ(�ǶԳƼ���)
		/encrypt		������Body����
		/decrypt		������Body����


	# ����ʹ�÷ǶԳƼ���
		* ����֤��
			keytool -genkey -alias mytestkey -keyalg RSA  -dname "CN=Config Server,OU=Unit,O=Organization,L=City,S=State,C=CN"   -keypass changeme -keystore server.jks -storepass letmein
	
		* ����������
			encrypt:
			  key-store:
				# keystore�ļ�λ��
				location: classpath:ssl/server.jks
				# ֤�����
				alias: mytestkey
				# keystore����
				password: letmein
				# JKS����keystore��֤������
				secret: changeme
				# ֤������
				type: JKS