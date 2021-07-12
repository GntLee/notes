---------------------
feign
---------------------
	# Open Feign
		* spring cloud �Լ������Ķ���

	# ��ַ
		https://github.com/spring-cloud/spring-cloud-openfeign
		https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/
		https://github.com/OpenFeign/feign
	
	# Maven
		<!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-openfeign -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
			<version>3.0.3</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-loadbalancer -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-loadbalancer</artifactId>
			<version>3.0.3</version>
		</dependency>
		 <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-cache</artifactId>
		</dependency>
		<dependency>
			<groupId>com.github.ben-manes.caffeine</groupId>
			<artifactId>caffeine</artifactId>
		</dependency>

		
		* ������ļ���������������
		* һ�㶼Ҫ���������ؾ��������
		* ���ؾ�������Ҫ��������ڱ��أ������Ƽ�ʹ��caffeine

	
	# ����ע��
		@EnableFeignClients
			String[] value() default {};
			String[] basePackages() default {};
				* ָ�� Feign�ӿ����ڵİ�

			Class<?>[] basePackageClasses() default {};
			Class<?>[] defaultConfiguration() default {};
				* ָ�� Feign ��ʶ�ӿ�

			Class<?>[] clients() default {};
				* �ֹ�ָ�� feign �ӿ���
		
	

	# �� Feign �ӿ�����ע��
		@FeignClient
			String value() default "";
			String name() default "";
				* ָ��������, �����Ƿ�����

			String contextId() default "";
			
			@Deprecated
			String qualifier() default "";
			String[] qualifiers() default {};
			String url() default "";
			boolean decode404() default false;
			Class<?>[] configuration() default {};
				* �Զ���������
				* ��������� Configuration �п���������Щ @Bean
					feign.codec.Decoder
					feign.codec.Encoder
					feign.Contract.

			Class<?> fallback() default void.class;
				* ָ�����񽵼���(����ʧ�ܵ����Է���)
				* ����Ӧ��ʵ�ֵ�ǰ��Client�ӿ�,���Ҹ�д�ӿڷ���,��Щ�������ǽӿڵĽ�������
			
			Class<?> fallbackFactory() default void.class;
				* ���񽵼������࣬��Ҫʵ�ֽӿ�: FallbackFactory<T>

			String path() default "";
				* ����·��URL

			boolean primary() default true;
				* �Ͷ�·��һ��ʹ�õ�ʱ��IOC����ڶ����ǰ�ӿڵ�ʵ�֣�ʹ�� @Autowired ע���ʱ��ͻᵼ�²�֪��ע���ĸ����Ӷ������쳣

				* Ĭ��Ϊ true ��spring �����е� Feign ʵ�ֶ������: @Primary ��ôʹ�� �ӿڽ���ע���ʱ�򣬾�Ĭ�ϻ�ѡ�� Feign ʵ�ֶ����Ƕ�·��ʵ��
				* ���Ҫȡ�� @Primary ��ע����ô���԰��������Ϊ false

---------------------
�������
---------------------

---------------------
feignһЩ��
---------------------
	# ��������Ҫʹ��ע������, ����ֻ֧����ԭʼ��springmvcע��, ������Ҫ����value����
		@RequestMapping
		@RequestHeader
		@RequestParam
		@RequestBody
		@RequestPart
		
		* ��ʵ��2.2�Ժ󣬾Ͳ��Ǳ�ѡ���ˣ������ǽ���д
		* ���е�������������д�ϣ����� consumer/producer ����

		* ����ʹ��Spring��ע�⣬����ʹ�� Feign ԭ����ע����һЩ����
	

		

	

	
