------------------ 
ȫ��ͨ������	  |
------------------ 
	management.endpoints.enabled-by-default=true
		* �Ƿ�����Ĭ�ϵĶ˵�(Ĭ�� true)
		* ����Ϊ false �Ļ�, ����(δ����)�Ķ˵㽫��Ӧ�ó�������������ȫɾ��

		* ���԰�������Ϊfalse, Ȼ�����ֶ�������app��Ҫ��¶�Ķ˵�, �Ӷ��������������Ĭ�϶˵�

	management.endpoints.jmx.exposure.exclude
	management.endpoints.jmx.exposure.include=*
	management.endpoints.web.exposure.exclude
	management.endpoints.web.exposure.include=info, health
		* ������jmx/http��ʽ��¶, ����¶�Ķ˵���Ϣ
		* ����д���, ʹ�ö��ŷָ�
		* ֧��ʹ��ͨ���: * ��ʾ����

	management.endpoints.web.base-path=/actuator
		* ���ʶ˵�ĸ�·��
	management.endpoints.web.path-mapping.<id>=/path
		* ����ָ��id�˵�ķ���·��
	

	management.endpoint.<id>.enabled=true
		* ����/����ָ��id�Ķ˵�

	management.endpoint.beans.cache.time-to-live=10s
		* �˵�Բ����κβ����Ķ�ȡ��������Ӧ�Զ�����
		* ͨ�������ã����ö˵�Ļ���ʱ��

------------------ 
CORS������		  |
------------------ 
	# CorsEndpointProperties

	management.endpoints.web.cors.allowed-origins=https://example.com
	management.endpoints.web.cors.allowed-methods=*
	management.endpoints.web.cors.allowed-headers=*
	management.endpoints.web.cors.exposed-headers=
	management.endpoints.web.cors.allow-credentials=true
	management.endpoints.web.cors.max-age=1800s




