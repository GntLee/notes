--------------------
����
--------------------
	Access-Control-Allow-Origin
		* ���ֶ��Ǳ���ġ�����ֵҪô������ʱOrigin�ֶε�ֵ��Ҫô��һ��:*����ʾ������������������
		* ����ͻ�������Я��ƾ֤����ô����ֵ������*����������ȷ��origin

	Access-Control-Allow-Credentials
		* ���ֶο�ѡ������ֵ��һ������ֵ����ʾ�Ƿ�������Cookie��
		* Ĭ������£�Cookie��������CORS����֮�С���Ϊtrue������ʾ��������ȷ��ɣ�Cookie���԰����������У�һ�𷢸���������
		* ���ֵҲֻ����Ϊtrue�������������Ҫ���������Cookie��ɾ�����ֶμ��ɡ�

	Access-Control-Allow-Methods
		* ���ֶα��裬����ֵ�Ƕ��ŷָ���һ���ַ���������������֧�ֵ����п�������ķ�����
		* ע�⣬���ص�������֧�ֵķ������������������������Ǹ�����������Ϊ�˱�����"Ԥ��"����

	Access-Control-Expose-Headers
		* CORS����ʱ��XMLHttpRequest�����getResponseHeader()����ֻ���õ�6�������ֶΣ�
			Cache-Control, Content-Language, Content-Type, Expires, Last-Modified, Pragma
		
		* ������õ������ֶΣ��ͱ�����Access-Control-Expose-Headers����ָ������

	Access-Control-Allow-Headers
		* ��������������� Access-Control-Request-Headers �ֶΣ���Access-Control-Allow-Headers�ֶ��Ǳ���ġ�
		* ��Ҳ��һ�����ŷָ����ַ���������������֧�ֵ�����ͷ��Ϣ�ֶΣ��������������"Ԥ��"��������ֶΡ�
		* ����ͻ�����ҪЯ��ƾ֤����ô���Header����������Ϊ: *

	Access-Control-Max-Age
		* ���ֶο�ѡ������ָ������Ԥ���������Ч�ڣ���λΪ�롣

