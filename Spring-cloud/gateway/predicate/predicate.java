------------------------------
Predicate ������
------------------------------
	# �������ļ��ж�� Predicate ֮��Ĺ�ϵ�� & ������Ż�ִ��

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
		* args ��·�ɹ�����������

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
