----------------------------
ע������					|
----------------------------
	# ����ע�����Ľڵ������Ҫ�޸�
		* ������֤��Ⱥ�ڵ������г�Ա��instance.name��һ��
		* ������ʽ���Դ��϶˿ڵ���Ϣ
		* ����ͨ������host�ļ�����ӳ��

		eureka.instance.name=localhost10086.com
		eureka.instance.name=localhost10087.com
		eureka.instance.name=localhost10088.com

	# ����ע���뷢��,Ĭ��ֵ����true,��ʵ�����ֶ�����
		fetch-registry: true
		register-with-eureka: true
	
	# ע���ַ���޸�
		* �� defaultZone ����ϵ�ǰ�ڵ���������нڵ��Ա��ע���ַ

		eureka.client.service-url.defaultZone=http://localhost10086.com:10086/eureka,http://localhost10088.com:10088/eureka
			* ��ǰ�ڵ�Ϊ:10087,����ֻ��Ҫ��� 86 �� 88 �ڵ�

	
	# ��������
		server:
		 port: 10088

		spring:
		 application:
		  # ����Ϊע������
		  name: register

		eureka:
		 instance:
		  # ��ǰ�ڵ��������
		  hostname: register3
		  # ��ǰ�ڵ��id(�ڿ���̨�е�����)
		  instance-id: ${eureka.instance.hostname}:${server.port} 
		 client:
		  service-url:
		   # ������ע�����ĵ�ַ
		   defaultZone: http://register1:10086/eureka/,http://register2:10087/eureka/
		
		* ��Ҫ��һ�����,ÿ���ڵ��id������ͬ,��������ֻ��һ���ڵ����õ����
	 
	 # �����ṩ�������������
		* ���ǽ�����Ҫ���ü�Ⱥ�е�����ڵ�ΪdefaultZone����
			service-url:
			 defaultZone: http://register3:10088/eureka/
		
		* Ҳ������������������ע�����ļ�Ⱥ�ڵ�
			service-url:
			 defaultZone: http://register3:10088/eureka/,http://register2:10087/eureka/,http://register1:10086/eureka/
		
		* ע�����ļ�Ⱥ�е�ÿ���ڵ�,���໥��ͬ������