------------------------
������				|
------------------------
	# ��ʵ����,����ͨ��һ���ӿ�����ȡ���������Ϣ
	# DiscoveryClient
	# α����

		@Autowired
		private DiscoveryClient discoveryClient;
		
		//���з����ṩ��
		List<String> services = discoveryClient.getServices();
		
		services.forEach(System.out::println);
		
		//��ȡָ������������ṩ����Ϣ
		List<ServiceInstance> instances = discoveryClient.getInstances("USER-SERVICE");

		for(ServiceInstance serviceInstance : instances){
			serviceInstance.getServiceId();
			serviceInstance.getHost();
			serviceInstance.getPort();
			serviceInstance.getUri();
		}