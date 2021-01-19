
------------------------
redisson �ֲ�ʽ��		|
------------------------
	# �����ĵ�
		https://github.com/redisson/redisson/wiki/8.-%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E5%92%8C%E5%90%8C%E6%AD%A5%E5%99%A8

	# ���Ź�����
		* ������𴢴�����ֲ�ʽ����Redis�ڵ�崻��Ժ�,������������ô�����ס��״̬ʱ,����������������״̬
		* Ϊ�˱�����������ķ���,Redisson�ڲ��ṩ��һ��������Ŀ��Ź�,������������Redissonʵ�����ر�ǰ,���ϵ��ӳ�������Ч��
		* Ĭ�������,���Ź��ļ�����ĳ�ʱʱ����30����,Ҳ����ͨ���޸� Config.lockWatchdogTimeout ������ָ��

------------------------
��������(Reentrant Lock)|
------------------------

	# ͬ�����Ĵ���
		RLock rLock = redissonClient.getLock("lock");

	# ͬ������
		//����,��Ҫ�ֶ�unlock
		void lock();

		//����,10s���Զ�����
		void lock(10, TimeUnit.SECONDS);

		//���Լ���,����ɹ�����true
		boolean rLock.tryLock();

		//���Լ���,���ȴ�100s,����ɹ�����(����true),��10s���Զ�����
		boolean tryLock(100, 10, TimeUnit.SECONDS);
		
		//�ֶ��ͷ���
		void unlock();
	
	# �첽����
		Future<Void> lockAsync();
		Future<Void> lockAsync(10, TimeUnit.SECONDS);
		Future<Boolean> rLock.tryLockAsync();
		Future<Void> tryLockAsync(100, TimeUnit.SECONDS);
		Future<Void> rLock.unlockAsync();

	# ����
		//�ж��Ƿ���Ի�ȡ��
		boolean rLock.isLocked();
		
------------------------
��ƽ��(Fair Lock)		|
------------------------
	# ����������һ��
		* ��ͬ����,����֤�˵����Redisson�ͻ����߳�ͬʱ�������ʱ,���ȷ�����ȷ���������߳�
	
	# ������
		RLock rLock = redisson.getFairLock("lock");
	
	# ��������ͬ��

------------------------
����(MultiLock)			|
------------------------
	# ���Խ����RLock�������Ϊһ������
		* ÿ��RLock����ʵ�����������ڲ�ͬ�� redissonClient ʵ��
	
	# ������
		RLock lock1 = redissonClient.getLock("lock1");
		RLock lock2 = redissonClient.getLock("lock2");
		RLock lock3 = redissonClient.getLock("lock3");

		RedissonMultiLock lock = new RedissonMultiLock(lock1, lock2, lock3);
		// ͬʱ������lock1 lock2 lock3
		// ���е����������ɹ�����ɹ���
		lock.lock();
		lock.unlock();
	
	# ��������ͬ��

------------------------
����(RedLock)			|
------------------------
	# RedissonRedLock����ʵ����Redlock���ܵļ����㷨,�ö���Ҳ�������������RLock�������Ϊһ������
		* ÿ��RLock����ʵ�����������ڲ�ͬ�� redissonClient ʵ��
	
	# ������
		RedissonRedLock lock = new RedissonRedLock(lock1, lock2, lock3);
		// ͬʱ������lock1 lock2 lock3
		// �����ڴ󲿷ֽڵ��ϼ����ɹ�����ɹ�
		lock.lock();
		lock.unlock();

------------------------
��д��(ReadWriteLoc)	|
------------------------
	# RReadWriteLock ����ʵ���� java.util.concurrent.locks.ReadWriteLock �ӿ�
		* ͬʱ��֧���Զ����ڽ���
		* �ö�������ͬʱ�ж����ȡ��,�������ֻ����һ��д����
	
	# ������
		RReadWriteLock rReadWriteLock = redissonClient.getReadWriteLock("lock");
		// ��ȡ����������
		rReadWriteLock.readLock().lock();
		// ��ȡд��������
		rReadWriteLock.writeLock().lock();;

------------------------
�ź���(Semaphore)		|
------------------------

------------------------------------------------
�ɹ������ź���(PermitExpirableSemaphore)		|
------------------------------------------------

------------------------
����(CountDownLatch)	|
------------------------
