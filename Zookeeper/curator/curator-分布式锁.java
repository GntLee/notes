-----------------------------
zk��						 |
-----------------------------
	# ���е�������:	InterProcessLock ����
		// ���Ի�ȡ�� - ����,ֱ����ȡ�ɹ�
		public void acquire() throws Exception;
		// ���Ի�ȡ�� - ����,ֱ����ʱ,���� boolean ��ʾ���Ƿ��ȡ�ɹ�
		public boolean acquire(long time, TimeUnit unit) throws Exception;
		// �ͷ���
		public void release() throws Exception;
		boolean isAcquiredInThisProcess();

-----------------------------
������						 |
-----------------------------
	#  ʵ����
		// ������
		InterProcessMutex interProcessMutex = new InterProcessMutex(client, "/node");
		// ���Ի�ȡ�� - ����,ֱ����ȡ�ɹ�
		interProcessMutex.acquire();
		// ���Ի�ȡ�� - ����,ֱ����ʱ,���� boolean ��ʾ���Ƿ��ȡ�ɹ�
		boolean result = interProcessMutex.acquire(1000, TimeUnit.SECONDS);
		// �ͷ���
		interProcessMutex.release();
	
-----------------------------
������						 |
-----------------------------		
	# ������������
	# ʵ����
		// ������
		InterProcessSemaphoreMutex interProcessSemaphoreMutex = new InterProcessSemaphoreMutex(client,"/lock");
		// ���Ի�ȡ�� - ����,ֱ����ȡ�ɹ�
		interProcessSemaphoreMutex.acquire();
		// ���Ի�ȡ�� - ����,ֱ����ʱ,���� boolean ��ʾ���Ƿ��ȡ�ɹ�
		interProcessSemaphoreMutex.acquire(1000, TimeUnit.SECONDS);
		// �ͷ���
		interProcessSemaphoreMutex.release();


-----------------------------
��д��						 |
-----------------------------
	# ������������
	# ʵ����
		InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(client, "/node");
		// ��ȡ����
		InterProcessMutex readInterProcessMutex = interProcessReadWriteLock.readLock();
		// ��ȡд��
		InterProcessMutex writeInterProcessMutex = interProcessReadWriteLock.writeLock();
		readInterProcessMutex.acquire();
		readInterProcessMutex.acquire(100, TimeUnit.SECONDS);
		readInterProcessMutex.release();


-----------------------------
�ź���						 |
-----------------------------
	# �߳�֮���ͬ��,Ϊ�˱�֤ͬһʱ��ֻ���޶��������߳̿��Է��ʵ���Դ
	# ʵ�� InterProcessSemaphoreV2
		// һ������100����Լ(ͬʱ�������100���̷߳���)
		InterProcessSemaphoreV2 interProcessSemaphoreV2 = new InterProcessSemaphoreV2(client,"/node", 100);

		// ���Ի�ȡһ����Լ,���û��,������ֱ����ȡ�ɹ�
		Lease lease = interProcessSemaphoreV2.acquire();
		
		// ���Ի�ȡһ����Լ,��ʱ���� null
		Lease lease interProcessSemaphoreV2.acquire(1000, TimeUnit.SECONDS);

		// ��ȡָ����������Լ,�������,������
		Collection<Lease> leases = interProcessSemaphoreV2.acquire(15);
	
		// ����һ����Լ
		interProcessSemaphoreV2.returnLease(lease);

		// ����һ����Լ
		interProcessSemaphoreV2.returnAll(leases);

-----------------------------
������						 |
-----------------------------
	# �ɶ������ɵ�һ����,���뵱���е��������ٳɹ�,������ɹ�
	# ʵ����
		InterProcessMultiLock(CuratorFramework client, List<String> paths);
			* Ĭ�ϸ���paths�½� InterProcessMutex ��

		public InterProcessMultiLock(List<InterProcessLock> locks);
			* ʹ��ָ������
		
		interProcessMultiLock.acquire();
		interProcessMultiLock.acquire(100, TimeUnit.SECONDS);
		interProcessMultiLock.release();