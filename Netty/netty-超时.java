----------------------------
��������					|
----------------------------
	# IdleStateHandler
		* ���̳���:ChannelDuplexHandler
		* �������ض���д

	# ���캯��
		public IdleStateHandler(boolean observeOutput, long readerIdleTime, long writerIdleTime, long allIdleTime, TimeUnit unit)
		public IdleStateHandler(int readerIdleTimeSeconds,int writerIdleTimeSeconds,int allIdleTimeSeconds)
		public IdleStateHandler(long readerIdleTime, long writerIdleTime, long allIdleTime,TimeUnit unit)
		
		observeOutput
			* �Ƿ��ǳ�վʱ���������,Ĭ��ֵ�� false(������)
			* ����ֶξ��������Ը� "�ͻ��˽������������ޱ�,�����ȿ���ʱ�仹��"�ļ������,���ԣ�Netty Ĭ���ǹر�����ֶε�

		readerIdleTime
			* �����г�ʱʱ���趨,���channelRead()��������readerIdleTimeʱ��δ��������ᴥ����ʱ�¼�����userEventTrigger()����
			* 0 ������¼�

		writerIdleTime
			* д���г�ʱʱ���趨,���write()��������writerIdleTimeʱ��δ��������ᴥ����ʱ�¼�����userEventTrigger()����
			* 0 ������¼�

		allIdleTime
			* ����д����ʱ���趨
			* 0 ������¼�

		unit
			* ʱ�䵥λ
	
		
	# ��������
		* ����ʱ������ʱ��,��handler�ͻᴥ��һ���û��Զ�����¼�,�����һ������:IdleStateEvent
		* ����ͨ���ö����ж���ʲô��ʱ�¼�������
		* �ö�����һ��ö�ٶ���(��ö����)
			IdleStateEvent.FIRST_READER_IDLE_STATE_EVENT
				* ��һ�η����˶���ʱ, ���Ǻܾ�û��ȡ���ͻ�����Ϣ��

			IdleStateEvent.READER_IDLE_STATE_EVENT
				* ���ǵ�һ�η����˶���ʱ,������ָ�״̬,��˵��channleĿǰ������������ʱ(����2��)
				
			IdleStateEvent.FIRST_WRITER_IDLE_STATE_EVENT
				* ��һ�η�����д��ʱ, ���Ǻܾ�û���ͻ���д������

			IdleStateEvent.WRITER_IDLE_STATE_EVENT
			IdleStateEvent.FIRST_ALL_IDLE_STATE_EVENT
				* ��һ�η���, ��д����ʱ��
			
			IdleStateEvent.ALL_IDLE_STATE_EVENT
	
	# �ܽ�
		* IdleStateHandler���������Ҫ��ͨ�����߳������������Ӷ�ʱ����,�ж�channelRead()������write()�����Ƿ���ÿ��г�ʱ
		* �����ʱ�򴥷���ʱ�¼�ִ���Զ���userEventTrigger()����

		* Nettyͨ��IdleStateHandlerʵ��������������Ʋ���һ��˫��������PING-PONGģʽ,���ǿͻ��˷����������ݰ�,����˽������������ظ�
		* ��Ϊ��������ͬʱ����ǧ������,�����Ļظ���Ҫ���Ĵ���������Դ
		* ��������һ��ʱ���������յ��ͻ��˵��������ݰ�����Ϊ�ͻ����Ѿ�����,��ͨ���رձ�����Դ���˷�
		* ����������ģʽ�·���˿��Ը�֪�ͻ��˵Ĵ�����,������崻����������߻�����������ķ���������,����˶��ܸ�֪��,���ͻ��˲��ܸ�֪������˵ķ���������

		* Ҫ��ʵ�ֿͻ��˸�֪����˵Ĵ�����,��Ҫ����˫�������
		* Netty�е�channelInactive()������ͨ��Socket���ӹر�ʱ�������ݰ�������,��˿���ͨ��channelInactive()������֪�������������,������Ϊ�����쳣�ȷ������������޷���֪

	
	# �����������Ƴ�ʱ�����
		ReadTimeoutHandler	
			��ָ��ʱ����û�н��յ��κ����ݽ��׳�	ReadTimeoutException

		WriteTimeoutHandler
			��ָ��ʱ������д�����ݽ��׳� WriteTimeoutException

----------------------------
�����Ľ���					|
----------------------------
	# �ͻ�������д��ʱ,����: 5s ��û��write�¼�,�ͻ᳢��������˷���һ��������
	# ͨ������������ĳɹ���������������Ƿ񻹴��

	# ����˲�����Ӧ���������,������Ӻܶ�(��ʮ���),��ô��Ӧ��������һ���ǳ����صĹ���

	# ����˿������ö���ʱ,����: 5s ��û�յ��ͻ��˵�����,��ʾ�ͻ��˿���崻�,�Ϳ���close()����


----------------------------
�ͻ��˶�������				|
----------------------------
	TODO
