-----------------------
��ʱ����
-----------------------
	# ȫ�ֿ���
		spring:
		  cloud:
			gateway:
			  httpclient:
				connect-timeout: 1000
				response-timeout: 5s
		
		* ȫ�����ã�֧�����ʱ�䵥λ�ַ���

	
	# ��Ե���·�ɿ���
		- id: per_route_timeouts
		  uri: https://example.org
		  predicates:
		    - name: Path
			  args:
			    pattern: /delay/{timeout}
		  metadata:
		    response-timeout: 200
		    connect-timeout: 200
		

		* �������÷�ʽ��response-timeout/connect-timeout ����ѡ�Ժ���ķ�ʽ����
	

	
		