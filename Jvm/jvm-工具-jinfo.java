------------------------
jinfo					|
------------------------
	# Configuration Info For Java
	# ��ʾ�������������Ϣ
		* ʵʱ�Ĳ鿴�͵���JVM�Ĳ���
	
	# �﷨
		jinfo [options] pid
		
		* �������Ӳ���(options), ���ӡ���ý�����ص�������Ϣ(JVM����, System.properties ����)

	# ����
		-flag [name]
			* �鿴ָ��jvmѡ���ֵ
			* ��: �鿴 -XX:CICompilerCount ��ֵ
				jinfo -flag CICompilerCount 3900
		
		-flags
			* �鿴ָ��JVMѡ�������ֵ(���û����, ����Ĭ��ֵ��)
		
		-sysprops
			* �鿴 System.properties ������
			* ���� key=value
	
	# ��̬������/����ĳЩJVM����
		jinfo -flag [+/-] [name]
	
	# ��̬���޸�JVM����
		jinfo -flag [name]=[value]

	
		
