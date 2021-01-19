------------------------
�����ṩ��				|
------------------------
	# maven����
		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
	
	
	# ���������Զ�ע�ᵽע������
		@SpringBootApplication
		@EnableEurekaClient		//��ǰΪeureka�Ŀͻ���
		public class UserServiceApplication {
			
			public static void main(String[] args) {
				SpringApplication.run(UserServiceApplication.class, args);
			}
		}
		* @EnableEurekaClient ��ʾ�˵�ǰ΢������ͨ�� eureka ��ܽ��з���ע���,����ͨ��������
		* ����ʹ��:@EnableDiscoveryClient ע��,��ע����һ���ӿ�,�������������з�������Ŀ��

	# ������(������:EurekaInstanceConfigBean)
		spring.application.name=example-user-service
			# ��ǰ΢���������,���Դ�д����ʽ������ eureka �Ŀ���̨

		eureka.client.service-url.defaultZone=http://localhost:10086/eureka/
			# ע�����ĵĵ�ַ
			# ���Ҫͬʱע�ᵽ���ע������,��ô���ע�����ĵ�ַ�Զ��Ÿ���

		eureka.instance.prefer-ip-address=true
			# ��eurake�������̨��,�÷������ӵĵ�ַ��ip����ʽ����,Ĭ��Ϊ������

		eureka.instance.instance-id=${spring.cloud.client.ipAddress}:${server.port}
			# �ڹܿ�̨��,ʵ�����ӵ�����
		
------------------------
�����ṩ��-info��Ϣ		|
------------------------
	# �������
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
	
	# pom.xml ����
		* ����ֱ��������prarent/pom.xml ��,�����Ӽ�ģ�鶼��ʹ��
		<build>
			<!-- ����src/main/resources��Դ���� -->
			<resources>
				<resource>
					<directory>src/main/resources</directory>
					<filtering>true</filtering>
				</resource>
			</resources>
			<plugins>
				<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-resources-plugin</artifactId>
					<configuration>
						<delimiters>
							<!-- ������ȡֵ�߽�� -->
							<delimit>$</delimit>
						</delimiters>
					</configuration>
				</plugin>
			</plugins>
		</build>
	
	# yml
		info: 
		 app.name: javaweb-community
		 company.name: javaweb
		 build.artifactId: $project.artifactId$
		 build.version: $project.version$
		
		* info ��ʵ����һ��map,�����������Զ����kv
		* ��Ϊmaven���������,����ʹ�� $$ ����ȡ�����汾����Ϣ

	# �ڹ������̨,�Ϳ�����json����ʽ��ͨ�� ..../info ����ȡ���������õ�info��Ϣ(���������ʧ����... ...)
		{"app":{"name":"javaweb-community"},"company":{"name":"javaweb"},"build":{"artifactId":"$project.artifactId$","version":"$project.version$"}}
	
	# eurek����̨�� info �ķ��ʵ�ַ�ǿ����޸ĵ�
		eureka.instance.statusPageUrlPath=/info

		* ��������ṩ�ߴ��� context-path ���ԵĻ�,�����޸��˽������ĵ�ַ,�ͱ���Ҫ��������
		* ������֧�־���·��,Ҳ����˵,�����ṩ�߼�������https�ṩ����:
			eureka.instance.statusPageUrlPath=https://localhost/info
------------------------
�����ṩ��-�������		|
------------------------
	# Ĭ�������ע�ᵽeureka server�ķ�����ͨ����������֪�Լ���UP����DOWN
	# Ĭ�ϵķ�ʽֻ��֪�������ṩ���Ƿ�����������,����֪�������Ƿ����������ṩ����

	# ����,�����޸��������Ϊ�������ķ�ʽ,ͨ����eureka�ͻ���������
		eureka.client.healthcheck.enabled=true

		* �Ϳ��Ըı�eureka server�Կͻ��˽������ķ�ʽ������actuator��/health�˵�����⡣
		* ���뵼��maven����
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-actuator</artifactId>
			</dependency>

	# �޸�eureka server���ʶ˵�Ľ�������ַ
		eureka.instance.healthCheckUrlPath=/health

		* ���뱣֤�õ�ַ���Է���,����ע�����Ĳ�����ݶ˵�Ľ���������޸Ķ˵��״̬
		* ��Ȼ,ǰ���ǿͻ��˿����˽������:eureka.client.healthcheck.enabled=true

		* ��������ṩ�ߴ��� context-path ���ԵĻ�,�����޸��˽������ĵ�ַ,�ͱ���Ҫ��������
		* ������֧�־���·��,Ҳ����˵,�����ṩ�߼�������https�ṩ����:
			eureka.instance.healthCheckUrlPath=https://localhost/info


--------------------------------
�����ṩ��-�������Լ��ʧЧ����	|
-------------------------------
	# Ĭ�Ϸ����ṩ�߻��ע�����ı���30sһ�ε�����
	# �������90s��û�յ�����,��ôע�����ľͻ�����÷�������
	# ���������Զ�����ͨ���������޸�
		eureka.instance.lease-renewal-interval-in-seconds
			* ������Լ����ĵ���ʱ����,Ĭ��30s
		eureka.instance.lease-expiration-duration-in-seconds
			* ����ʱЧ��ʱ��,Ĭ��90s,����˵������û���յ��������Ƿ���ʧЧ,�ͻ�ѷ�����б��Ƴ�

------------------------
�����ṩ��-��������		|
------------------------
	# ���������Ĺر�,�ᷢ��һ��rest�����ע������,��֪ע�����ĵ�ǰ�ڵ����ߵ���Ϣ
	# ע�������յ���Ϣ���ɸýڵ�����,���ҰѸ��¼��㲥��ȥ
