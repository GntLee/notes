------------------------
ע������				|
------------------------
	# maven����
		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
	
	# ���� eureka server
		@SpringBootApplication
		@EnableEurekaServer			//ͨ�� @EnableEurekaServer ע�⿪�� eureka ע������
		public class RegisterApplication {
			public static void main(String[] args)throws Exception{
				SpringApplication.run(RegisterApplication.class,args);
			}
		}


	# ������,������(EurekaInstanceConfigBean,EurekaServerConfigBean)
		
		eureka.instance.name=localhost
			# eureka����˵�ʵ������
		eureka.client.fetch-registry=false
			# ��ǰeureka ������Ϊע������(server),����ȥ��������
		eureka.client.register-with-eureka=false
			# ��ǰeureka�����䵱ע������,�����Լ���Ϊ�����ṩ�ߵ�ע����Ϊ
			

		eureka.client.service-url.defaultZone=http://localhost:${server.port}/eureka/
			# �����ṩ�߽���ע��ĵ�ַ,���Ǿ߱�Ĭ��ֵ��


------------------------
���ұ�������			|
------------------------
	# Ĭ�������,Eureka��һ��ʱ����û�н��յ�ĳ��΢����ʵ��������,Eureka��ע����ʵ��(Ĭ��90��)
		* ����,������������Ϸ���ʱ,΢������Eureka֮���޷�����ͨ��,������Ϊ���ܱ�÷ǳ�Σ����
		* ��Ϊ΢�������ǽ�����,'��Ӧ��ע����΢����',Eurekaͨ��'���ұ�������',������������
		* ��Eurake�ڵ��ڶ�ʱ���ڶ�ʧ�˹���Ŀͻ���(�����ṩ��)ʱ,��ô����ڵ�ͻ�������ұ���ģʽ
		* �����ģʽ��,Eurake�ᱣ������ע����е���Ϣ,���ٽ���ɾ�����е�����(Ҳ����˵����ע���κη���)
		* ��������ϻָ�ʱ,�Զ��˳����ұ���ģʽ
		* eureka������,Ĭ��ÿ��60s���һ��΢�����ʵ���Ƿ�down��

	# �����ұ���ģʽ��,Eurake�ᱣ��ע����е���Ϣ,����ɾ���κη���ʵ��
		* �����յ������������»ָ�����ֵ����ʱ,Eurake Server�ڵ�ͻ��Զ��˳����ұ���ģʽ
		* ���������ѧ����:'���ɱ�������ķ���ע����Ϣ,Ҳ��äĿע���κο��ܽ����ķ���ʵ��'

	# ���ұ���ģʽ��һ�ֶ�Ӧ'�����쳣�İ�ȫ��������ʩ'
		* ���ļܹ���ѧ������ͬʱ��������΢����(��ص�΢����,�Ͳ������ķ��񶼻ᱣ��)
		* ʹ�����ұ���ģʽ,������Eurake��Ⱥ���ӽ�׳�ĵ�

	# �������ұ���ģʽ
		eureka.server.enable-self-preservation=false
	
	# ���ֻ�����,�ͻ����ܻ��ȡ��ʧЧ�ķ���,����Ҫ��ͻ��˱���Ҫ���ݴ����(����,�۶�)


------------------------
��ȫ��ע������			|
------------------------
	# ע����������
		1,���security����
			<dependency> 
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-security</artifactId>
			</dependency>

		2,�����û���������
			spring.security.user.name=KevinBlandy
			spring.security.user.password=123456

		3,�ڷ���·���ϼ����û�������
			eureka.client.serviceUrl.defaultZone=http://${spring.security.user.name}:${spring.security.user.password}@localhost:${server.port}/eureka/
			* ע���ʽ: �û���:����@������:�˿�
	
		* ����̨Ҳ��Ҫʹ�ø��û����������¼
	
	# �ͻ��˵�����
		
		spring.security.user.name=KevinBlandy
		spring.security.user.password=123456

		eureka.client.serviceUrl.defaultZone=http://${spring.security.user.name}:${spring.security.user.password}@localhost:10086/eureka/

		* �ͻ��˲���security������,ֻ����ע�����ĵĵ�ַ������û���������
	
	# springboot2.0�Ժ�securityĬ�Ͽ�����csrf,���ܵ��¿ͻ��˵�ע��ʧ��(��Ӧ�ͻ���401״̬��)
		* ���Գ����ڷ���˹ر�csrf

		@EnableWebSecurity
		public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

			@Override
			protected void configure(HttpSecurity http) throws Exception {
				http.csrf().disable();
				super.configure(http);
			}
		}