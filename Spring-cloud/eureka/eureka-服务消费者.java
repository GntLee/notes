------------------------
����������				|
------------------------
	# maven����
		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
        </dependency>
	
	# ע������
		@EnableEurekaClient
		* @EnableEurekaClient ��ʾ��΢������ͨ�� eureka ��ܽ��з���ע���,����ͨ��������
		* ����ʹ��:@EnableDiscoveryClient ע��,��ע����һ���ӿ�,�������������з�������Ŀ��

	# ������
		eureka.client.register-with-eureka:false
			# ����������,��ע��
		eureka.client.service-url.defaultZone:http://localhost10086.com:10086/eureka,http://localhost10087.com:10087/eureka,http://localhost10088.com:10088/eureka
			# ע�����ĵĵ�ַ,���Eurekaע�������Ǽ�Ⱥ,�����ý����еļ�Ⱥ�ڵ���Ϣ
		
	
	# ����������
		
		//΢����ĵ�ַ(��ʵ��������),Ҫ��д
		private static final String SERVICE_NAME = "http://USER-SERVICE";
		
		@Autowired
		private RestTemplate restTemplate;

		@GetMapping
		public ResponseEntity<Void> test(User user){
			this.restTemplate.postForObject(SERVICE_NAME + "/add",user,JSONObject.class);
		}
		

		* ע�� RestTemplate ��ʱ��Ҫ��� @LoadBalanced,��ʾ�������ؾ���
			@Bean
			@LoadBalanced
			public RestTemplate restTemplate() {
				return new RestTemplate();
			}

			* �����ҷ������û�и�ע��,��ô΢���񽫻����ʧ��

------------------------
�����ṩ���б���		|
------------------------
	# ����������ÿ30s��ע�����Ļ�ȡ���µķ����ṩ���б�,���ҽ��л���
	# ����ͨ�������޸ĸ�ʱ��ֵ
	
		eureka.client.registry-fetch-interval-seconds

		* ��ֵĬ��30s,ÿ��30s��ע�����Ļ�ȡһ�η����ṩ�ߵ��б�
