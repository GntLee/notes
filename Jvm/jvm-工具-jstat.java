-------------------------
jstat					 |
-------------------------
	# JVM Statistics Tool
		* �ռ� HotSpot ��������������������
	
	# ���JVM����״̬�������й���
		* ������ʾ���ػ���Զ��������е���װ��, �ڴ�, �����ռ�, JIT��������в���

	# ��Windows��GUIͼ�ν���,��Linux���ı�ͳ����, ������  top ����Ľ��

	# �����ʽ
		jstat [option vmid [interval[s|ms] [count]] ]

		vmid
			* ����Ǳ��ص�JVM, ����ǽ���ID, �����Զ�̵�: [protocal:][//]lvmid[@hostname:[:port]/servername]
		
		interval
			* ��ʾ��ѯ���, ʱ�䵥λ�Ǻ���
		
		count
			* ��ʾ��ѯ�ܴ���
			* Ĭ��ֻ���ѯһ��
			* �������Ϊ : -1, ���������
		
	
	# ��ѡ����(option)
		-class
			* ������װ��, ж������, �ܿռ��Լ����ķѵ�ʱ��
			* �������
				Loaded  Bytes  Unloaded  Bytes     Time   
				1913  3906.6        8     6.7       4.74
				1913  3906.6        8     6.7       4.74

		-gc
			* ���Ӷ�״��, ������������������, �Ѿ�ʹ�õĿռ�, GCʱ��ϼƵ���Ϣ
			* �������
				S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT   
				1408.0 1408.0  0.0   1408.0 11840.0  10459.3   29264.0    21233.5   13056.0 12766.8 1536.0 1407.4   9885   12.705  65      1.641   14.346

		-gccapacity
			* ���������� -gc һ��, ���������Ҫ��ע Java �Ѹ�������ʹ�õ������, ��С�ռ�
			* �������
				NGCMN    NGCMX     NGC     S0C   S1C       EC      OGCMN      OGCMX       OGC         OC       MCMN     MCMX      MC     CCSMN    CCSMX     CCSC    YGC    FGC 
				10240.0 156992.0  14656.0 1408.0 1408.0  11840.0    20480.0   314048.0    29264.0    29264.0      0.0 1060864.0  13056.0      0.0 1048576.0   1536.0   9885    65

		-gcutil
			* ���������� -gc һ��, ����Ҫ�����ע��ʹ�ÿռ�ռ�ܿռ�İٷֱ�
			* �������
				S0     S1     E      O      M     CCS    YGC     YGCT    FGC    FGCT     GCT   
				0.00 100.00  88.34  72.56  97.79  91.62   9885   12.705    65    1.641   14.346

				S0:Survivor0 �ǿյ�
				S1:Survivor1 ��װ����
				E: Eden �Ѿ�ʹ���� 88.34% �Ŀռ�
				O: Old, �����, �Ѿ�ʹ���� 72.56% �Ŀռ�
				M: Metaspace, Ԫ�ռ�, �Ѿ�ʹ���� 97.79% �Ŀռ�
				P: JDK8��ǰ��������
				CCS:
				YGC: ���������ڼ�, ����MinorGC 9885 ��
				YGCT:���������ڼ�, ����MinorGC ����ʱ: 12.705 �� 
				FGC:���������ڼ�, ����FullGC 65 �� 
				FGCT:���������ڼ�, ����FullGC ����ʱ:1.641 ��
				GCT:���������ڼ�, ����GC�ܺ�ʱ: 14.346 ��

				

		-gccause
			* ���������� -gcutil һ��, ���ǻ�������������һ��GC��ԭ��
			* �������
				S0     S1     E      O      M     CCS    YGC     YGCT    FGC    FGCT     GCT    LGCC                 GCC                 
				0.00 100.00  88.34  72.56  97.79  91.62   9885   12.705    65    1.641   14.346 Allocation Failure   No GC 
		
		-gcnew
			* ����������GC״̬
			* �������
				S0C    S1C    S0U    S1U   TT MTT  DSS      EC       EU     YGC     YGCT  
				1408.0 1408.0    0.0 1408.0  1  15  704.0  11840.0  10459.3   9885   12.705

		-gcnewcapacity
			* �������ݺ� -gcnew ��ͬ, �����Ҫ��עʹ�õ������, ��С�ռ�
			* �������
				NGCMN      NGCMX       NGC      S0CMX     S0C     S1CMX     S1C       ECMX        EC      YGC   FGC 
				10240.0   156992.0    14656.0  15680.0   1408.0  15680.0   1408.0   125632.0    11840.0  9885    65

		-gcold
			* ���������GC״̬
			* �������
				MC       MU      CCSC     CCSU       OC          OU       YGC    FGC    FGCT     GCT   
				13056.0  12766.8   1536.0   1407.4     29264.0     21233.5   9885    65    1.641   14.346

		-gcoldcapacity
			* �������ݿ��� -gcold ��ͬ, �����Ҫ��עʹ�õ������, ��С�ռ�
			* �������
				OGCMN       OGCMX        OGC         OC       YGC   FGC    FGCT     GCT   
				20480.0    314048.0     29264.0     29264.0  9885    65    1.641   14.346

		-gcpermcapacity
			* ������ô�ʹ�õ������, ��С�ռ�
			* JDK8��, û�����ô���, ʹ��Ԫ�ռ� metaspace ����
			
		-compiler
			* ���JIT ������������ķ���, ��ʱ����Ϣ
			* �������
				Compiled Failed Invalid   Time   FailedType FailedMethod
				2234      0       0     9.15          0    

		-printcompilation
			* ����Ѿ���JIT����ķ���
			* �������
				Compiled  Size  Type Method
				2234     17    1 io/netty/channel/AbstractChannel$AbstractUnsafe voidPromise
				2234     17    1 io/netty/channel/AbstractChannel$AbstractUnsafe voidPromise

