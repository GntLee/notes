----------------------
RequestSize
----------------------
	# ��������Ĵ�С����λ��byte�������趨ֵ����413 Payload Too Large
	
	
	# ����
		spring:
		  cloud:
			gateway:
			  routes:
			  - id: request_size_route
				uri: http://localhost:8080/upload
				predicates:
				  - Path=/upload
				filters:
				  - name: RequestSize
					args:
					  maxSize: 5000000
		

		* Ĭ�����õ�λ���ֽ�
		* ����ʹ���ַ���: KB/MB
			maxSize: 5KB
		
		* ���һ��·��û���������Filter�Ļ���Ĭ���������Ϊ: 5MB
	
		


