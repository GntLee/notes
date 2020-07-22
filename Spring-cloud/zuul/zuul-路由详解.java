----------------------------
����ӳ�����				|
----------------------------
	# ��ʵ������(��ͳ)
		zuul:
		 routes:
		  user-api:
		   path:/api/**					**/
		   url:http://springboot.io/
		
		*  /api/user/1 �ᱻת���� -> http://springboot.io/user/1
	
	#  ��ʵ������(�������)
		zuul:
		 routes:
		  user-api:
		   serviceId: USER
		   path:/api/**					**/
		
		ribbon.eureka.enabled=false
		USER.ribbon.listOfServers=http://localhost:8080/,http://localhost:8081/

		* ���ǰѶ�path�ķ���,ת����������ΪUSER�ķ����б�
		* USER�Ǹ��Զ���ķ�������


	# ����·������
		zuul:
		 ignored-service: USER
		 routes:
		  user:
		   serviceId: USER
		   path: /myuser/**								**/

		
		* ignored-service ���ԷǱ���,���Ĵ��ھ��Ƿϳ�ָ�������ԭ�з��ʵ�ַ(/{������}/{uri})
			* Ҳ����˵,����ʹ��routes������µ�ַ
			* �����ֵΪ: * (�Ǻ�),���ʾ�ϳ����е�΢����ԭʼ���ʵ�ַ

		* routes ����һ��Map
		* ���涨����:
			·������.serviceId: ��������
			·������.path: �����µķ��ʵ�ַ
		
		* http://����ip:���ض˿�/·������/�ӿڵ�ַ
	

	# ����ͳһ����ǰ׺
		zuul:
		 predix: /api
	
		* ���е�΢����ǰ�涼Ҫ��: /api
	
----------------------------
�Զ���·��ӳ�����			|
----------------------------
	# ʵ����:PatternServiceRouteMapper
		
		@Bean
		public PatternServiceRouteMapper patternServiceRouteMapper(){
			return new PatternServiceRouteMapper("<name>","${name}");
		}

		* ����ʹ��������ƥ���ת����·��
	
	# ���캯��
		public PatternServiceRouteMapper(String servicePattern, String routePattern)
			servicePattern
				* ��������ƥ�����

			routePattern
				* ·�ɵ�ƥ�����
	
----------------------------
������ת					|
----------------------------
	# ������ת
	zuul:
	  ignored-services: "*"
	  routes:
		api-user:
		  path: /user-api/****/
		  # ת�������ص�: /local/demo ��
		  url: forward:/local/demo

----------------------------
Cookie ��ͷ��Ϣ				|
----------------------------
	# zuul������·�ɵ�ʱ��,����˵�����ͷ��Ϣ�е�һЩ������Ϣ,��ֹ���Ǳ����ݵ����η�����
	# ����(���г����ľ���Ĭ��ֵ):
		zuul:
		  sensitive-headers:
			- Cookie
			- Set-Cookie
			- Authorization
	
		* �����Լ���д��
	
	# Ҳ������Է����������
		zuul.routes.<router>.customSensitiveHeaders=true
			* ��ָ����·�ɿ����Զ�������ͷ

		zuul.routes.<router>.sensitiveHeaders=
			* ��ָ��·�ɵ�����ͷ����Ϊ��


