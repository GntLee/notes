-----------------------------
RewritePath
-----------------------------
	# ��дURI
	# ������
		RewritePathGatewayFilterFactory

		* ��������
			private String regexp;
			private String replacement;
		
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
		

		* һ�㶼����� Path ����ʹ�õ� **/
		* ����YAML�淶����Ҫ��:$ ��Ҫ�滻Ϊ: $\ �������ʹ���ַ�������������Ҫ�����磺replacement: "/${segment}"
