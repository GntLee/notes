------------------------------
Predicate ������
------------------------------

	# �������
		spring:
		  cloud:
			gateway:
			  routes:
			  - id: after_route
				uri: https://example.org
				predicates:
				  - Cookie=mycookie,mycookievalue
		
		* ����������ʶ�𣬺���Ⱥ� ( =)������ɶ��� ( ,)�ָ��Ĳ���ֵ

	
	# ��������
		spring:
		  cloud:
			gateway:
			  routes:
			  - id: after_route
				uri: https://example.org
				predicates:
				  - name: Cookie
				    args:
					  name: mycookie
					  regexp: mycookievalue
		

		* name ָ������
		* args �����Ͼ���һ�� Map<String, String>, �����Զ���һЩ Predicate ��Ҫ������

------------------------------
ϵͳԤ����� Predicate 
------------------------------
	ZoneDateTime
	Cookie
	Header
	Host
	Method
	Path
	Query
	RemoreAddr
