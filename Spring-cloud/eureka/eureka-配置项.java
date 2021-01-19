----------------------------
Eureka��������				|
----------------------------
	# �˵���Ϣ������
		* ��Ҫ�ǶԶ˵����������Ԫ����

	# �������ĵ�����
		* Eureka�������ĵ��������

	# �ͻ��˵�����
		* �˵��һЩ����
	
	# ������
		EurekaServerConfigBean
		EurekaInstanceConfigBean
		EurekaClientConfigBean

----------------------------
Eureka��������				|
----------------------------

	eureka.instance.name
		# �ڵ������
	eureka.instance.hostname
		# �ڵ����������
	eureka.instance.instance-id
		# �ڵ��id,����ͬһ�������еĲ�ͬ�ڵ�
			* ͬһ�������нڵ��id������ͬ,��Ȼ�����ֻ��һ���ڵ����õ����
	eureka.instance.lease-renewal-interval-in-seconds
		# ������Լ����ĵ���ʱ����,Ĭ��30s
	eureka.instance.lease-expiration-duration-in-seconds
		# ����ʱЧ��ʱ��,Ĭ��90s,����˵������û���յ��������Ƿ���ʧЧ
	eureka.instance.statusPageUrlPath
		# �޸ķ���ʵ����Ԫ��Ϣ�ĵ�ַ·��,Ĭ��:/info
	eureka.instance.healthCheckUrlPath
		# �޸ķ���ʵ������״̬�ĵ�ַ·��,Ĭ��:/health

	eureka.client.fetch-registry
		# �Ƿ��ע�����ķ��ַ���
	eureka.client.registry-fetch-interval-seconds
		# ÿ���������ע�����Ļ�ȡһ�������ṩ���б���Ϣ,Ĭ��30s
	eureka.client.register-with-eureka
		# �Ƿ�ע�����ע������
	eureka.client.service-url.defaultZone
		# ע�����ĵĵ�ַ
		# service-url ��һ�� ��Ϊ serviceUrl �� HashMap
			* Ĭ�ϵ� key:defaultZone ,value:http://localhost:8761/eureka/
	eureka.client.healthcheck.enabled
		#  �Ƿ�ͨ�� healthcheck ���������ṩ�ߵ�״̬


	eureka.server.enable-self-preservation
		# �Ƿ������ұ���ģʽ,Ĭ�� true

----------------------------
��Ӧ������Bean				|
----------------------------
	# ����˵�������òο�bean
		org.springframework.cloud.netflix.eureka.server.EurekaServerConfigBean

		* ��Bean��������Զ�����:eureka.server ��ͷ��


	# �ͻ��˵�������òο�bean
		org.springframework.cloud.netflix.eureka.EurekaClientConfigBean

		* ��Bean��������Զ�����:eureka.client ��ͷ��
	
	# �˵���Ϣ������Bean
		org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean

		* ��Bean��������Զ�����:eureka.instance ��ͷ��
		* ��Bean���������һ��������Ϊ: metadataMap �� HashMap,Ҳ����˵֧��,�Զ���ļ�ֵ����
			eureka.instance.metadataMap.name=KevinBlandy
