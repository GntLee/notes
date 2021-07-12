---------------
Path
---------------
	# ����URI��ƥ��·�ɣ��õ�����һ����

	# ������
		patterns
			* ��һ�����飬����һ�����߶��URI

		matchTrailingSlash: true
			* �Ƿ�ƥ��β��б��(/)
			* matchTrailingSlash����Ϊfalse���� /red/1/ ���󲻻�ƥ������·�� /red/{segment} 

	
	# ����
		spring:
		  cloud:
			gateway:
			  routes:
				- id: user_router
				  uri: lb://user-service
				  predicates:
					- Path=/api/user/**
				  filters:
					- RewritePath=/api/user/?(?<segment>.*), /$\{segment}
		

		* һ�㶼Ҫ��� RewritePath ���������ʹ�ã�������дPath����·��ǰ׺ɾ������ȥ������� **/
	

	# ֧��ģ�����
		spring:
		  cloud:
			gateway:
			  routes:
			    - id: path_route
				  uri: https://example.org
				  predicates:
				    - Path=/red/{segment},/blue/{segment}
		

		* ��� segment ��洢�� ServerWebExchange.getAttributes() �У� key����:ServerWebExchangeUtils.URI_TEMPLATE_VARIABLES_ATTRIBUTE
		* ���Կ�ݻ�ȡ
			Map<String, String> uriVariables = ServerWebExchangeUtils.getPathPredicateVariables(exchange);
			String segment = uriVariables.get("segment");
			
