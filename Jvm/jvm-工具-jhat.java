---------------------
jhat				 |
---------------------
	# JVM Heap Dump Browser
	
	# ���ڷ��� heapdump �ļ�,���Ὠ��һ�� HTTP/HTML ������, ����ֱ����������ϲ鿴�������
		* ���������û���߲鿴 dump �ļ��Ľ��������, ���򲻽���ʹ����
		* ��Ϊһ�㲻���ڲ���Ӧ�õķ�������ȥ����dump�ļ�, ��Ϊ���������ǳ�������Ӳ����Դ, ��ʱ
		* ���ķ�������Ҳ�Ƚϵļ�ª, һ�㲻����
	
	# ����ʹ�õķ�����
		Eclipse Memory Analyzer
		IMB HeapAnalyzer
	
	# ����
		jhat [options] [dump]

		dump: ָ��dump�ļ�
	
		* Ĭ���� 7000 �˿��ṩ http ����
			Reading from heapdump...
			Dump file created Mon May 27 13:39:33 GMT+08:00 2019
			Snapshot read, resolving...
			Resolving 279684 objects...
			Chasing references, expect 55 dots.......................................................
			Eliminating duplicate references.......................................................
			Snapshot resolved.
			Started HTTP server on port 7000
			Server is ready.
	
	# ����
		-port
			* ָ���˿ں�
	
