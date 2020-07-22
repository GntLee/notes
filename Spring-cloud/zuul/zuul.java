----------------------------
zuul						|
----------------------------
	# Zuul�����˶������:·��,����,����  ...�Ⱥ��ĵ���Ҫ����
		
	# ·��
		* ������ⲿ����ת���������΢����ʵ����
		* ��ʵ���ⲿ����ͳһ��ڵĻ���

	# ����
		* ���������Ĵ�����̽��и�Ԥ
		* ��ʵ������У��,����ۺϵȹ��ܵĻ���
	
	# Zuul��Eureka����,��Zuul����ע��ΪEureka���������µ�Ӧ��,ͬʱ��Eureka�л�ȡ����΢�������Ϣ
		* �Ժ����΢����,����ͨ��Zuul��ת����
	
	# 'Zuul�������ջ��ǻ�ע���Eureka'

	# Maven
		<!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-zuul -->
		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-zuul</artifactId>
        </dependency>

----------------------------
zuul-��������				|
----------------------------
	# ����
		* ��ҲҪ����һ������,ע�ᵽEureka��
		* ����,��Ҳ��Ҫ�����ṩ�ߵ���һ��eureka����

		spring:
		  application:
			name: zuul
		  security:
			user:
			  name: springcloud
			  password: 123456
		eureka:
		  client:
		    # ��Ҫ��ע�����Ļ�ȡ����
			fetch-registry: true
			# ��Ҫ���Լ�ע�ᵽע������
			register-with-eureka: true
			service-url:
			  defaultZone: http://${spring.security.user.name}:${spring.security.user.password}@localhost:8081/eureka/
	
	# ����ע��
		@EnableZuulProxy

		* ��ʶע��,û���κε�����
	
	# ͨ��·�ɽ��з���
		* Э��:��������:�˿�:��������/�ӿ�

		http://localhost:8081/user-service/user/info/1
	
	# ͨ��Feign����
		// ZUULҲ��ע�ᵽeureka�ķ���
		@FeignClient(value = "ZUUL")
		// ͨ�� /{������}/{uri} ���÷���
		@RequestMapping("/user-service/user")
		public interface UserService {
			
			@GetMapping(value = "/info/{userId}")
			Object userInfo(@PathVariable("userId")Integer userId);
		}
				

----------------------------
hystrix �� Ribbon��֧��		|
----------------------------	
	# zuul����Ͱ�����hystrix��Ribbon,�����������̸߳���Ͷ�·�������ұ�������,�Լ��Է���˸��ؾ�����õĹ���
		* ��ʹ�� path �� url ��ӳ���ϵ������·�ɵ�ʱ��,�������۶Ϻ͸��ؾ���Ĺ���
		* ����Ҫ������ʹ�� path �� service-id ������·��
	
	# ����
		hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds
			* ���÷�����õĳ�ʱʱ��,��λ�Ǻ���
			* �����ʱ,��Ѹ�ִ��������ΪTIMEOUT,�����׳��쳣,��Ӧ�쳣JSON�����÷�
		
		ribbon.ConnecTimeout
			* ����·��ת�������ʱ��,�������ӵĳ�ʱʱ��
			* ��ֵӦ��С�ڷ�����õĳ�ʱʱ��,��Ϊһ�����ִ������ӳ�ʱ,ϵͳ�᳢�Խ�������
			* �������ʧ��,����Ӧʧ����Ϣ�����÷�
		
		ribbon.readTimeout
			* ����·�ɽ�����,��ȡ������Ӧ�ĳ�ʱʱ��
			* ��ֵӦ��С�ڷ�����õĳ�ʱʱ��,��Ϊһ�����ֶ�ȡ�ӳ�ʱ,ϵͳ�᳢�Խ�������
		
		zuul.retryable=true
			* ����ͨ�������ùر�/�������Ի���
		
		zuul.routes.<route>.retryable=false
			* �������·��ӳ��,�����Ƿ�Ҫ����ʧ�����Ի���
	

------------------------
��̬·��				|
------------------------
	# ·�ɵ����ù���,��������ͨ��config�����õ�
	# ��� springcloud-config ,�Ķ�̬ˢ�»��ƾͿ��Զ�̬��ˢ��·�ɹ���