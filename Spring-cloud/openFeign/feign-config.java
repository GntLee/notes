------------------
config
------------------
	# �ĵ�
		https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/appendix.html
	

------------------
config
------------------
	feign.compression.request.enabled=true
	feign.compression.response.enabled=true
		* �Ƿ�������/��Ӧѹ��
	feign.compression.request.mime-types=text/xml,application/xml,application/json
		* ����ֻ����Щ���͵����������ѹ��
	feign.compression.request.min-request-size=2048
		* ��Сѹ�����
	feign.compression.response.useGzipDecoder=true
		* Ĭ��ʹ��gzip����Ӧѹ��

	feign.autoconfiguration.jackson.enabled=true
		* ���� Jackson Modules ��֧�� org.springframework.data.domain.Page �� org.springframework.data.domain.Sort ����

	feign.client.refresh-enabled=true
		* ���������refresh��ÿ��feign�ͻ��˶����� feign.Request.Options ��Ϊ scope ��Χ��bean�������ġ�
		* ����ζ������connectTimeout��readTimeout�����Կ���ͨ�� POST /actuator/refresh ���κ�Feign�ͻ���ʵ������ˢ��
		* Ĭ��Ϊ false

		* ������ @FeignClient ����� @RefreshScope ע��

	feign.okhttp.enabled=false
		* ʹ��okhttp�ͻ���
	
	feign.metrics.enabled=true
		* ����ָ��
	
	
		