----------------------------
hystrix����					|
----------------------------
	# feign��Ϊ����fegin�ͻ��˵ķ�������װ��Hystrix�����н��з��񱣻�

	# ����,�ر�Hystrix
		fegin.hystrix.enabled=true/false
	
	# �ر��۶Ϲ���
		hystrix.command.default.execution.timeout.enabled=false
	
	# ȫ������
		hystrix.command.default.<key>=<value>
		
		* �ڶ�hystrix�������õ�ʱ��,���뱣֤��������״̬
			fegin.hystrix.enabled=true

----------------------------
����hystrix					|
----------------------------
	# ȫ�ֹر�
		fegin.hystrix.enabled=false
	
	# �����ĳ��������йر�
		* ��Ҫͨ�� @Scope("prototype") ע����Ϊ�ͻ���ָ�� Feign.Builder ʵ��
			
			@Configuration
			public DisableHystrixConfigration{
				@Bean
				@Scope("prototype")
				public Feign.Builder feignBuilder(){
					return Feign.Builder();
				}
			}
		
		* �ӿ�����
			@Feign(value = "USER-SERVICE",configuration = {DisableHystrixConfigration.class})


----------------------------
ָ����������				|
----------------------------
----------------------------
���񽵼�����				|
----------------------------

