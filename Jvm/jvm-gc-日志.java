----------------------------------
GC��־							  |
----------------------------------
	# ÿ���ռ�������־��ʽ���ܲ�һ��
	# �����Ϊ�˷����Ķ�, ��ÿ���ռ�������־��ά����һ���Ĺ���

0.513: [GC (System.gc()) [PSYoungGen: 5012K->1258K(38400K)] 5012K->1266K(125952K), 0.0014624 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
0.196: [Full GC (System.gc()) [PSYoungGen: 1258K->0K(38400K)] [ParOldGen: 8K->1156K(87552K)] 1266K->1156K(125952K), [Metaspace: 3473K->3473K(1056768K)], 0.0054130 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 

	* ��ǰ������ֱ�ʾJVM������ʱ��, ����

	* GC/Full GC ��ʾGC������, �����������ĸ������
	* �����Full GC, �϶��Ƿ����� stop the world ��

	* �ռ���������,����:ParNew
	* (System.gc()), ��ʾ���� System.gc() �����������ռ�

	* ��ʾGC����������, ����������ƺ��ռ��������������еĹ�ϵ
		DefNew(Serial �ռ���)
		ParNew(ParNew �ռ���)
		PSYoungGen(Parallel Scavenge �ռ���)
		Tenured
		Perm
	
	* 5012K->1258K(38400K)
		* GCǰ���ڴ���ʹ���� ->  GC���ڴ���ʹ����(��������ڴ�������)
	
	* 5012K->1266K(125952K)
		* Java����ʹ�õ����� -> GC�����ʹ�õ�����(Java��������)
	
	* 0.0014624 secs
		* ������GC��ռ�õ�ʱ��
	
	* [Times: user=0.00 sys=0.00, real=0.00 secs] 
		* ��Ϊ��ϸ��ʱ������, user,sys,real ��Linux��time���������ʱ�京��һ��
		user:�û�̬����CPU��ʱ��
		sys:..
		radl..
	

	Heap
		PSYoungGen      total 38400K, used 333K [0x00000000d5a00000, 0x00000000d8480000, 0x0000000100000000)
			eden space 33280K, 1% used [0x00000000d5a00000,0x00000000d5a534a8,0x00000000d7a80000)
			from space 5120K, 0% used [0x00000000d7a80000,0x00000000d7a80000,0x00000000d7f80000)
			to   space 5120K, 0% used [0x00000000d7f80000,0x00000000d7f80000,0x00000000d8480000)
	ParOldGen       total 87552K, used 1156K [0x0000000080e00000, 0x0000000086380000, 0x00000000d5a00000)
		object space 87552K, 1% used [0x0000000080e00000,0x0000000080f21140,0x0000000086380000)
	Metaspace       used 3481K, capacity 4496K, committed 4864K, reserved 1056768K
		class space    used 376K, capacity 388K, committed 512K, reserved 1048576K