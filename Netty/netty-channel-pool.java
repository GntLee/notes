---------------------
pool				 |
---------------------
	# ���ͻ��˵ĵĶ���,���ͻ��˵ĵĶ���,���ͻ��˵ĵĶ���
	# Ŀ���ǽ��, ��������˽����Լ��뵥������˽������ӳص�����

	# ������
		ChannelPool
			|-AbstractChannelPoolHandler
				|-SimpleChannelPool
					|-FixedChannelPool
		ChannelPoolHandler
			
		ChannelHealthChecker
			
		ChannelPoolMap
			|-AbstractChannelPoolMap
	
	# ���
		ChannelPool
			* ���ӳؽӿ�

		SimpleChannelPool
			* ʵ��ChannelPool�ӿ�, �򵥵����ӳ�ʵ��

		FixedChannelPool
			* �̳�SimpleChannelPool, �д�С���Ƶ����ӳ�ʵ��

		ChannelPoolMap
			* ����host�����ӳ�ӳ��Ľӿ�

		AbstractChannelPoolMap
			* ������,ʵ��ChannelPoolMap�ӿ�

----------------------
ChannelPool			  |
----------------------
	# ���ӳصĳ���ӿ�, ʵ���� Closeable �ӿ�
	# ���󷽷�
		Future<Channel> acquire();

		Future<Channel> acquire(Promise<Channel> promise);

		Future<Void> release(Channel channel);

		Future<Void> release(Channel channel, Promise<Void> promise);

		@Override
		void close();



----------------------
SimpleChannelPool	  |
----------------------
	# �򵥵����ӳ�ʵ��
	# ���캯��
		SimpleChannelPool(Bootstrap bootstrap, final ChannelPoolHandler handler)
		SimpleChannelPool(Bootstrap bootstrap, final ChannelPoolHandler handler, ChannelHealthChecker healthCheck)
		SimpleChannelPool(Bootstrap bootstrap, final ChannelPoolHandler handler, ChannelHealthChecker healthCheck, boolean releaseHealthCheck)
		SimpleChannelPool(Bootstrap bootstrap, final ChannelPoolHandler handler, ChannelHealthChecker healthCheck, boolean releaseHealthCheck, boolean lastRecentUsed)


----------------------
FixedChannelPool	  |
----------------------
	# �̶����������ӳ�ʵ��, �̳� SimpleChannelPool
	# ���캯��
		FixedChannelPool(Bootstrap bootstrap, ChannelPoolHandler handler, int maxConnections)
		FixedChannelPool(Bootstrap bootstrap, ChannelPoolHandler handler, int maxConnections, int maxPendingAcquires)
		FixedChannelPool(Bootstrap bootstrap, ChannelPoolHandler handler, ChannelHealthChecker healthCheck, AcquireTimeoutAction action, final long acquireTimeoutMillis, int maxConnections, int maxPendingAcquires)
		FixedChannelPool(Bootstrap bootstrap, ChannelPoolHandler handler, ChannelHealthChecker healthCheck, AcquireTimeoutAction action, final long acquireTimeoutMillis, int maxConnections, int maxPendingAcquires, final boolean releaseHealthCheck)
		FixedChannelPool(Bootstrap bootstrap, ChannelPoolHandler handler, ChannelHealthChecker healthCheck, AcquireTimeoutAction action, final long acquireTimeoutMillis, int maxConnections, int maxPendingAcquires, boolean releaseHealthCheck, boolean lastRecentUsed)
	
		
		action
			* �ڲ���̬ö����:FixedChannelPool.AcquireTimeoutAction
				NEW
					* ��⵽��ʱʱ����������
				FAIL
					* �׳��쳣:TimeoutException
