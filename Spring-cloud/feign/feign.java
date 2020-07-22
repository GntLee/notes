------------------------
feign					|
------------------------
	# ����һ������ʽ��WebService�ͻ���
		* ʹ��Feign���ñ�дWebService�ͻ��˸��ӵļ�
		* ����ʹ�÷����Ƕ���һ�����,Ȼ���ڽӿ������ע��,ͬʱҲ֧��JAX-RS��׼��ע��
		* FeignҲ֧�ֿ��Ȱβ�ʽ�ı���ͽ�����

	# SpringCloud��Feign�����˷�װ,ʹ��֧����SpringMVC��׼ע���HttpMessageConverters

	# Feign���Ժ�Eureka,Ribbon���ʹ��,��֧�ָ��ؾ���
		* FeignĬ�ϼ�����Ribbon,Hystrix,����Ribbonά����΢�����ṩ�ߵ��б���Ϣ,����ͨ����ѯʵ���˿ͻ��˵ĸ��ؾ���
		* ��Ribbon��ͬ,feignֻ��Ҫ�������󶨽ӿ���������ʽ�ķ���,�򵥶������ŵ�ʵ���˷���ĵ���

	# ����
		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>

		* ������Ҫ������
		* ����Ҫ�ظ�������:ribbon,hystrix

------------------------
��������				|
------------------------
	# ע�⿪��
		@EnableFeignClients
		@SpringBootApplicatoon
		@EnableEurekaClient

			* ͨ�� @EnableFeignClients ������Feign����
		

	# ����ӿ�
		@FeignClient(value = "USER-SERVICE")
		@RequestMapping("/user")
		public interface UserService {

			@GetMapping(value = "/info/{userId}")
			Object userInfo(@PathVariable("userId")Integer userId);
		}

		
		* @FeignClient ͨ����ע��,ָ��΢���������
		* ʹ��·��ע��(@GetMapping)��ָ������·��,ͨ�� @PathVariable ���󶨲���
		* ��mybatis��mapperһ��,��̬����ʵ������IOC��
	
	# ����
		feign.hystrix.enabled: true
			* �����۶���,���������� fegin��������

	# ʹ�� FeignClient �ӿ�
		
		@Autowired
		private UserService userService;
		
		@GetMapping("/user")
		public User getUser(@RequestParam("id")int id){
			//�Խӿڵ���ʽ����΢����
			return this.userService.userInfo(id);
		}
	
--------------------
ע�����ϸ����		|
--------------------
	# @EnableFeignClients
		String[] value() default {};
		String[] basePackages() default {};
		Class<?>[] basePackageClasses() default {};
		Class<?>[] defaultConfiguration() default {};
		Class<?>[] clients() default {};
	
	# @FeignClient
		@AliasFor("name")
		String value() default "";
		@Deprecated
		String serviceId() default "";
		String contextId() default "";
		@AliasFor("value")
		String name() default "";
		String qualifier() default "";
		String url() default "";
		boolean decode404() default false;
		Class<?>[] configuration() default {};
		Class<?> fallback() default void.class;
			* ָ�����񽵼���(����ʧ�ܵ����Է���)
			* ����Ӧ��ʵ�ֵ�ǰ��Client�ӿ�,���Ҹ�д�ӿڷ���,��Щ�������ǽӿڵĽ�������

		Class<?> fallbackFactory() default void.class;
		String path() default "";
		boolean primary() default true;

------------------------
����ѹ��				|
------------------------
	# fegin֧�ֶ��������Ӧ����zipѹ��,���ٴ���Ĵ���

feign:
  hystrix:
    enabled: true
  compression:
    request:
	  # ��������ѹ��
      enabled: true 
	  # ֧��ѹ����ContentType
      mime-types:
        - text/xml
        - application/xml
        - application/json
	  # ִ��ѹ������С���
      min-request-size: 2048
    response:
	  # �Ƿ�ѹ����Ӧ
      enabled: true

------------------------
��־����				|
------------------------
	# feign��Ϊ @FeignClient �����ͻ��˵�ʱ��,��Ϊÿһ���ͻ��˶�����һ��: feign.Logger ʵ������
		* �������ø���־�����DEBUGģʽ������Feign������ϸ��
	
	# ����ָ���ͻ��˵���־
		1,���ÿͻ��˵���־
			logging.level.<FeignClient>=DBUG

			* FeignClient���ǿͻ��˵Ľӿ�ȫ·��
		
		2,����ȫ�� Logger.Level
			@Bean
			public Logger.Level feignnLoggerLevel(){
				return Logger.Level.FULL;
			}

			* feignĬ�ϵ� Logger.Level ������ΪNONE,�����¼�κ�Feign���ù����е���Ϣ
	
		3,�����ָ������ָ�� Logger.Level
			@Configuration 
			public class FullLogConfiguration{
				@Bean
				public Logger.Level feignnLoggerLevel(){
					return Logger.Level.FULL;
				}
			}
			
			@FeignClient(value = "USER-SERVICE",configuration = {FullLogConfiguration.class})
		
	# Feign��Logger��Ҫ�� �ĸ�
		NONE
			* ����¼�κ���Ϣ
		BASIC
			* ������¼����ʽ,URL,��Ӧ״̬���ִ��ʱ��
		HEADERS
			* ����BASIC���⻹���¼����ͷ����Ӧͷ��Ϣ
		FULL
			* ��¼��ż




