----------------------------
��������					|
----------------------------
	# ���
		TrafficCounter
			* ͳ�Ƶ�����
			|-GlobalChannelTrafficCounter

		AbstractTrafficShapingHandler
			* ��������handler
			|-ChannelTrafficShapingHandler
			|-GlobalChannelTrafficShapingHandler
			|-GlobalTrafficShapingHandler
	
	
	# �������ε�ԭ��
		���� -> ������� -> ����(����Ͱ) -> ��� -> ƽ������
	
	# AbstractTrafficShapingHandler
		* ����handler�ĳ�����

		AbstractTrafficShapingHandler()
		AbstractTrafficShapingHandler(long checkInterval)
		AbstractTrafficShapingHandler(long writeLimit, long readLimit)
		AbstractTrafficShapingHandler(long writeLimit, long readLimit, long checkInterval)
		AbstractTrafficShapingHandler(long writeLimit, long readLimit)
		AbstractTrafficShapingHandler(long writeLimit, long readLimit, long checkInterval, long maxTime)
		
		writeLimit
			* ����û�����д�����kb����
			* �������Ϊ0,��ʾ������

		readLimit
			* ����û������ȡ����kb����
			* �������Ϊ0,��ʾ������

		checkInterval
			* �������ܼ���֮����ӳ�,Ĭ��1s
			* �������Ϊ0,��ʾ��ͳ��
			
		maxTime
			* ����������ʱ�ȴ�������ӳ�,Ĭ��15s

		
----------------------------
ChannelTrafficShapingHandler|
----------------------------
	# ���� Channel �����IO����
		ChannelTrafficShapingHandler(long checkInterval)
		ChannelTrafficShapingHandler(long writeLimit,long readLimit)
		ChannelTrafficShapingHandler(long writeLimit,long readLimit, long checkInterval)
		ChannelTrafficShapingHandler(long writeLimit, long readLimit,long checkInterval, long maxTime)
	


-----------------------------------
GlobalChannelTrafficShapingHandler |
-----------------------------------
	# �����ĳ�������е����� Channel ��IO����
		GlobalChannelTrafficShapingHandler(ScheduledExecutorService executor)
		GlobalChannelTrafficShapingHandler(ScheduledExecutorService executor, long checkInterval)
		GlobalChannelTrafficShapingHandler(ScheduledExecutorService executor, long writeGlobalLimit, long readGlobalLimit, long writeChannelLimit, long readChannelLimit)
		GlobalChannelTrafficShapingHandler(ScheduledExecutorService executor,long writeGlobalLimit, long readGlobalLimit,long writeChannelLimit, long readChannelLimit, long checkInterval)
		GlobalChannelTrafficShapingHandler(ScheduledExecutorService executor,long writeGlobalLimit, long readGlobalLimit,long writeChannelLimit, long readChannelLimit, long checkInterval, long maxTime)


-----------------------------------
GlobalTrafficShapingHandler			|
-----------------------------------
	# ȫ�ּ����ͬʱ��ȫ��,�͵��� Channel ��IO����(������������)
		GlobalTrafficShapingHandler(EventExecutor executor)
		GlobalTrafficShapingHandler(ScheduledExecutorService executor, long checkInterval)
		GlobalTrafficShapingHandler(ScheduledExecutorService executor, long writeLimit,long readLimit)
		GlobalTrafficShapingHandler(ScheduledExecutorService executor, long writeLimit,long readLimit, long checkInterval)
		GlobalTrafficShapingHandler(ScheduledExecutorService executor, long writeLimit, long readLimit,long checkInterval, long maxTime)
