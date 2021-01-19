--------------------
jmap				|
--------------------
	# Memory Map For Java
	# ������������ڴ�ת�������ļ�(heapdump)
	
	# ����Java�Ķ�ת������, ����ͨ��һЩ�ֶ�
		* ʹ��:-XX:+HeapDumpOnOutOfMemoryError ���ڴ������ʱ��, �����ڴ����
		* ��Linux��ʹ��: kill -3 ����'�Ż�JVM', Ҳ���õ�����
	
	# �����ʽ
		jmap [options] vmid

	
	# ����
		-dump
			* ����Java��ת������
			* �ò����м����Ӳ���, ��'-dump:'�����, ���Ӳ���, ʹ�ö��ŷָ�
				-dump:live,format=b,file=D:\\heapdump

				live	�ò�����ʾ,����dump�����Ķ���
				format	ָ����ʽ��??
				file	ָ���ļ���·��

			* ���ɿ���ʾ��
				jmap -dump:format=b,file=C:\Users\KevinBlandy\Desktop\heapdump 3900
			
			* ��������ļ��Ѿ�����, ��ᴴ��ʧ��
		
		-finalizerinfo
			* ��ʾ��:F-Queue �еȴ� Finalier �߳�ִ�� finalize �����Ķ���
		
		-heap
			* ��ʾJava����ϸ��Ϣ, ��:ʹ�õĻ���, ��������, �ִ�״̬
		
		-histo
			* ��ʾ���еĶ���ͳ����Ϣ, ������, ʵ������, �ϼ�����
			* �ò�����һ���Ӳ���
				live	����ͳ�ƴ��Ķ���
		
		-clstats
			* �� ClassLoader Ϊͳ�ƿھ�, ��ʾ�ڴ�״̬
		
		-F
			* ��JVM���̶� -dump ѡ��û����Ӧ��ʱ��, ����ʹ�ø�ѡ��ǿ������ dump ����
			* ֻ�� Linux/Solarisƽ̨��Ч


