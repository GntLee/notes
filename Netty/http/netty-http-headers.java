----------------------
HttpHeaders
----------------------
	# abstract class HttpHeaders implements Iterable<Map.Entry<String, String>>
		* ������
		* Ĭ��ʵ��
			DefaultHttpHeaders
			EmptyHttpHeaders
				EmptyHttpHeaders.INSTANCE // Ĭ�ϵĿ���Ӧͷʵ��

			ReadOnlyHttpHeaders
	
	
	# ��̬����
		

	# ��̬����
		

	# ʵ������
		HttpHeaders add(HttpHeaders headers)
		HttpHeaders add(CharSequence name, Iterable<?> values)
		HttpHeaders add(CharSequence name, Object value)
		HttpHeaders addInt(CharSequence name, int value)