---------------------------------
ʹ���Լ���֤��
---------------------------------
	*  Ĭ��ʹ�õ�Let��s Encrypt ���һ������ֻ����һ��ʱ����ǩ�� 5 �Σ�����㳬����ǩ���Ĵ���������Ҫ 5 �������ٴ����롣
		[Sun 04 Oct 2020 04:52:58 AM UTC] Create new order error. Le_OrderFinalize not found. {
		"Error creating new order :: too many certificates already issued for exact set of domains: www.ossez.com: see https://letsencrypt.org/docs/rate-limits/"
	

	1. ��֤���ļ��ϴ���������Ŀ¼
		* /var/discourse/shared/standalone/ssl
			�ļ���	����		����·��
			ssl.key	key �ļ�	/var/discourse/shared/standalone/ssl/ssl.key
			ss.crt	crt �ļ�	/var/discourse/shared/standalone/ssl/ssl.crt
			
		*  �ļ����Ʊ���һ��
	
	2. �޸� app.yml
		vi /var/discourse/containers/app.yml

		* ȡ��ע�� (����Ѿ���ע�͵Ļ�)
			- "templates/web.ssl.template.yml" 
		
		* ���ע��
			- "templates/web.letsencrypt.ssl.template.yml"
	
		* ����������������ע�͵���ͼ���ǣ����� ssl �����ã����ǳ���ʹ�� letsencrypt �� ssl ǩ����
		
	3. ���Ŷ˿�
		* ȷ��expose������ 80 �� 443
	
	4. ���¹���app
		./launcher rebuild app
	

		