---------------------
�����Զ�·��
---------------------
	# �Զ�Ϊ�������·��
		spring.cloud.gateway.discovery.locator.enabled=true
			* Ϊÿ������ע��һ��·�ɣ�������з�������ǰ׺��/<service-name>  �ͻ�·�ɵ�ָ���ķ���
			* ִ��Զ������֮ǰ���Զ�ɾ��ǰ��� <service-name>
			
		spring.cloud.gateway.discovery.locator.lower-case-service-id=true
			* ����������Сд
	
	# Demo
		* �и��������ƽ���: user-service �ڶ˿� 8080 �ṩ����
		* ��������
			/user-service/demo ---> ת�� ---> /demo:8080
	
	# ����ν�ʺ͹�����
		spring.cloud.gateway.discovery.locator.predicates[0].name: Path
		spring.cloud.gateway.discovery.locator.predicates[0].args[pattern]: "'/'+serviceId+'/**'"
		spring.cloud.gateway.discovery.locator.predicates[1].name: Host
		spring.cloud.gateway.discovery.locator.predicates[1].args[pattern]: "'**.foo.com'"

		spring.cloud.gateway.discovery.locator.filters[0].name: CircuitBreaker
		spring.cloud.gateway.discovery.locator.filters[0].args[name]: serviceId
		spring.cloud.gateway.discovery.locator.filters[1].name: RewritePath
		spring.cloud.gateway.discovery.locator.filters[1].args[regexp]: "'/' + serviceId + '/?(?<remaining>.*)'"
		spring.cloud.gateway.discovery.locator.filters[1].args[replacement]: "'/${remaining}'"


		
---------------------
�����ֶ�·��
---------------------
	# ͨ������·��
		spring:
		  cloud:
			gateway:
			  routes:
				- id: user_router
				  uri: lb://user-service		# ͨ�� lb ָ������ID
				  predicates:
					- Path=/api/user/**			# ͨ��ǰ׺����·�ɵ�����
				  filters:
					- RewritePath=/api/user/?(?<segment>.*), /$\{segment} # ʹ��Filter��д·����Ŀ����ɾ��ǰ���·��ǰ׺
