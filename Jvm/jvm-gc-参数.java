------------------------
�ռ����Ĳ����ܽ�		|
------------------------	
	-XX:+UseSerialGC
		* Client ģʽ��Ĭ��ֵ, ������ģʽ��ʹ��:Serial + Serial Old ���
		* ����������, ���괮��

	-XX:+UseParNewGC
		* �򿪺�, ʹ��:ParNew + Serial Old ���
		* ����������, ���������
	
	-XX:+UseConcMarkSweepGC 
		* ʹ��:ParNew + CMS + Serial Old ���
		* ��CMS����:Concurrent Mode Failure��ʱ��, ����ʱʹ�� Serial Old �ռ��������¶���������������ռ�
		* ����������, ���������(����ʧ��,��ʱת��Ϊ����)
		* ���Ը�ҵ���̲߳���

	-XX:+UseParallelGC 
		* ��Serverģʽ�µ�Ĭ��ֵ, ʹ��:Parallel Scavenge + Serial Old  ���(PS MarkSweep)���

	-XX:+UseParallelOldGC
		* ʹ��:Parallel Scavenge + Parallel Old

	-XX:SurvivorRatio
		* ������Eden�����Survivor�����������ֵ, Ĭ��Ϊ:8 (Ҳ����˵Eden=80%)

	-XX:PretenureSizeThreshold
		* ������������Ķ����С, ���øò�����, ������ڸò����Ķ���, ֱ�������������
		* Ĭ��ֵ��0, ��˼�ǲ��ܶ��������Eden�з����ڴ�
		* ����ֻ��:Serial �� ParNew �����ռ�����Ч
	
	-XX:MaxTenuringThreshold
		* ������������Ķ�������, ÿ�������ڼ�ֹ�һ��:Minor GC ��, ����ͻ� +1
		* ��������ֵ��ʱ��, �ͻ���������, Ĭ��Ϊ 15 
		

	-XX:+UseAdaptiveSizePolicy
		* ������Ӧ GC ����, ������ģʽ��, ������һЩ���Բ���Ҫ�Լ�ȥ����, �����ᱻ�Զ�����, �Դﵽ�ڶѴ�С, ��������ͣ��ʱ��֮���ƽ���
			-Xmn(��������С)
			-XX:+SuivivorRatio(Eden��Survivor���ı���)
			-XX:+PretenureSizeThreshold(�����������������)
		* ʹ������ӦGC����, ֻ��Ҫ�ѻ������ڴ��������ú�,������ڴ��Сֵ
		* Ȼ�������ע/�������ͣ��ʱ��:-XX:MaxGCPauseMillis 
		* ���߸�JVM����һ����������� -XX:GCTimeRatio ���Ż�Ŀ��, ����Ĺ���ϸ�ھ���jvm���
	
	-XX:HandlePromotionFailure
		* �Ƿ�������䵣��ʧ��, ���������ʣ��ռ䲻����Ӧ���������������ж��󶼴��ļ������
		* ����ʧ�ܺ��ִ��Full GC
	
	-XX:ParallelGCThreads
		* ���� ParNew �ռ������ռ��߳�����

	-XX:GCTimeRatio
		* ������������С, ����ֵ��һ������ 0 С�� 100 ֮�������
		* �������Ϊ: �����ռ�ʱ��ռ��ʱ��ı���
		* Ĭ�� GCTimeRatio ��ֵΪ 99, ��ôϵͳ�����Ѳ����� 1 / (1 + 99) = 1% ��ʱ�����������ռ�
	
	-XX:MaxGCPauseMillis
		* ����������ռ�ͣ��ʱ��, ����ֵ��һ������ 0 ������
		* �ռ����ڹ���ʱ, ����� Java �Ѵ�С��������һЩ����,�����ܵذ�ͣ��ʱ������� MaxGCPauseMillis ����
		* ͣ��ʱ�������, ��������������(��ǰ10sһ��100ms��GC, ����5sһ��70ms��GC)���������ռ�(�����С���ڴ��ռ��ȽϿ�)������, ��Ҳ����GC�����ø��ӵ�Ƶ��
		* ��С�Ļ�, GCͣ��ʱ��ȷʵ�½���,������������Ҳ�½���
	
	-XX:CMSInitiatingOccupancyFraction
			* ����CMS�ռ�����������ռ䱻ʹ�ö��ٺ���������ռ�,Ĭ��68%
			* �����������������Ǻܿ�, �����ʵ��ĵ��߲���(0 - 100 �ٷֱ�), ����GC����
			* ���CMS�����ڼ�,Ԥ�����ڴ治����ҵ���̵߳�ʹ��, �ͻ����һ��:Concurrent Mode Failure ʧ��
			* ����JVM������Ԥ��, ��ʱ����:Serial Old �ռ��������¶���������������ռ�
			* Ҳ����˵����̫��, ���ܻᵼ�´�����Concurrent Mode Failure ʧ��, ���ܷ�������
		
	-XX:+UseCMSCompactAtFullCollection
		* ������CMS����סҪ����Full GC��ʱ��, �����ڴ���Ƭ�ĺϲ��������
		* �ڴ�����Ĺ���û������, �ռ������������, ����ҵ���߳�ͣ��ʱ�䲻�ò��䳤��
	
	-XX:CMSFullGCsBeforeCompaction
		* ����ִ�ж��ٴβ�ѹ����FullGC��, ������һ�δ�ѹ����
		* Ĭ��Ϊ0, ��ʾÿ�ν���Full GCʱ��������Ƭ����

	-XX:+UseG1GC
		* ʹ��G1�ռ���