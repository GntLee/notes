---------------------
Host 
---------------------
	# ���������HOST����·��
		* һ�㳡�����ǣ�ʹ������ǰ׺�����ַ���
	
	# ʵ����
		HostRoutePredicateFactory

		* ����������
			private List<String> patterns = new ArrayList<>();
	
	# ����
		spring:
		  cloud:
			gateway:
			  routes:
			  - id: host_route
				uri: https://example.org
				predicates:
				  - Host=**.somehost.org,**.anotherhost.org
		

		* ����ʹ��ͨ���: **������ָ�������ʹ�ö��ŷָ�
	
	# ֧��ģ��������õ�ʱ��飬��Դ��Ҳ��
		