--------------------------
jvm����					  |
--------------------------

--------------------------
jvm����	ͳ��			  |
--------------------------

-verbose:gc -Xms20m -Xmx20m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
-verbose:class -XX:+TraceClassLoading -XX:+TraceClassUnloading

-Xmx
	* ���ڴ����ֵ

-Xms
	* �ѳ�ʼ����С
	* ��ֵ����������-Xmx��ͬ, �Ա���ÿ������������ɺ�JVM���·����ڴ�

-Xmn
	* �������ڴ��С

-Xss
	* ÿ���̵߳�ջ�Ĵ�С

-Xnoclassgc
	* �ر�Class�Ļ���

-verbose:gc
-verbose:class

-XX:PermSize
-XX:MetaspaceSize
-XX:MaxPermSize
-XX:MaxMetaspaceSize
-XX:MaxDirectMemorySize
	* �������Ķ����ڴ��С

-XX:MinMetaspaceFreeRatio
-XX:MaxMetaspaceFreeRatio

-XX:+DisableExplicitGC
	* ��ֹ��ʾ�ĵ���GC(System.gc())

-Xloggc
	* ָ��gc��־�ļ��ĵ�ַ, �������������׼�����
		-Xloggc:../logs/gc.log

-XX:+HeapDumpOnOutOfMemoryError
	* ���ڴ������ʱ��, �����ڴ����

-XX:HeapDumpPath
	* ���ڴ������ʱ��, �洢�ڴ���յ��ļ���
		-XX:HeapDumpPath=C:\Users\KevinBlandy\Desktop
	
	* �ļ����Ƹ�ʽ: java_pid[pid].hprof
		java_pid7560.hprof


-XX:+PrintGC
	*  ��ӡGC
-XX:+PrintHeapAtGC
	* ��GCǰ�󶼴�ӡ��־��Ϣ

-XX:+PrintGCDetails
	* ��ӡGC��־����
	* ������JVM�˳���ʱ��, ��ӡ�����ڴ������ʹ�����

-XX:+PrintGCTimeStamps  
	* ��ӡGCʱ��,JVM����ʱ��

-XX:+PrintGCDateStamps 
	* ���GC��ʱ���(�����ڵ���ʽ, �� 2013-05-04T21:53:59.234+0800)

-Xloggc:
	* ����GC��Ϣ��ָ�����ļ�
		-Xloggc:C:\Users\KevinBlandy\Desktop\gc.log
	* ����ļ��Ѿ�����, �ᱻ��д

-XX:+TraceClassLoading
	* ��ӡ�������Ϣ

-XX:+TraceClassUnLoading
	* ��ӡ��ж����Ϣ

-XX:+PrintFlagsFinal
	* ��ӡ��JVM���е�����������Ϣ
	* �����������е�������Ϣ, �����ֶ����õ�, �Լ�ϵͳĬ�ϵ�


	










-XX:ParallelGCThreads
	* ���� ParNew �ռ������ռ��߳�����

-XX:MaxGCPauseMillis
	* ����������ռ�ͣ��ʱ��, ����ֵ��һ������ 0 ������
	* �ռ����ڹ���ʱ, ����� Java �Ѵ�С��������һЩ����,�����ܵذ�ͣ��ʱ������� MaxGCPauseMillis ����
	* ͣ��ʱ�������, ��������������(��ǰ10sһ��100ms��GC, ����5sһ��70ms��GC)���������ռ�(�����С���ڴ��ռ��ȽϿ�)������, ��Ҳ����GC�����ø��ӵ�Ƶ��
	* ��С�Ļ�, GCͣ��ʱ��ȷʵ�½���,������������Ҳ�½���

-XX:GCTimeRatio
	* ������������С, ����ֵ��һ������ 0 С�� 100 ֮�������
	* �������Ϊ: �����ռ�ʱ��ռ��ʱ��ı���
	* Ĭ�� GCTimeRatio ��ֵΪ 99, ��ôϵͳ�����Ѳ����� 1 / (1 + 99) = 1% ��ʱ�����������ռ�

-XX:NewRatio
	* ��������������������ڴ����
	* ����Ϊ4 -XX:NewRatio=4, ������������ϴ���ռ��ֵΪ 1 : 4, �����ռ������ջ��1/5

-XX:SurvivorRatio
	* �����������ڴ��� Eden �� Survivor�ı���ֵ
	* Ĭ�� -XX:SurvivorRatio=8 , Ҳ����˵ Eden ռ 80%�ڴ�

-XX:PretenureSizeThreshold
	* ������������Ķ����С, ���øò�����, ������ڸò����Ķ���, ֱ�������������
	* Ĭ��ֵ��0, ��˼�ǲ��ܶ��������Eden�з����ڴ�
	* ����ֻ��:Serial �� ParNew �����ռ�����Ч

-XX:MaxTenuringThreshold
	* ������������Ķ�������, ÿ�������ڼ�ֹ�һ��:Minor GC ��, ����ͻ� +1
	* ��������ֵ��ʱ��, �ͻ���������
	* ��������ֵ��ʱ��, �ͻ���������, Ĭ��Ϊ 15 

-XX:+HandlePromotionFailure
	* �Ƿ�������䵣��ʧ��, ���������ʣ��ռ䲻����Ӧ���������������ж��󶼴��ļ������
	* ����ʧ�ܺ��ִ��Full GC

-XX:+UseConMarkSweepGC
	* ʹ��Concurrent Mark Sweep(CMS)��Ϊ������ռ���
	* ���ʹ�øò���, Ĭ�Ͼͻ�ʹ��: ParNew ��Ϊ���������ռ���

-XX:+UseParNewGC
	* ǿ��ϵͳʹ�� ParNew ��Ϊ���������ռ���

-XX:+UseAdaptiveSizePolicy
	* ������Ӧ GC ����, ������ģʽ��, ������һЩ���Բ���Ҫ�Լ�ȥ����, �����ᱻ�Զ�����, �Դﵽ�ڶѴ�С, ��������ͣ��ʱ��֮���ƽ���
		-Xmn(��������С)
		-XX:+SuivivorRatio(Eden��Survivor���ı���)
		-XX:+PretenureSizeThreshold(�����������������)
	* ʹ������ӦGC����, ֻ��Ҫ�ѻ������ڴ��������ú�,������ڴ��Сֵ
	* Ȼ�������ע/�������ͣ��ʱ��:-XX:MaxGCPauseMillis 
	* ���߸�JVM����һ����������� -XX:GCTimeRatio ���Ż�Ŀ��, ����Ĺ���ϸ�ھ���jvm���


-XX:+SerialGC
-XX:+UseParallelGC
-XX:+UseParallelOldGC
-XX:+UseG1GC