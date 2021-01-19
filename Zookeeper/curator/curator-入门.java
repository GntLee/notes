-------------------------------------
curator								 |
-------------------------------------
	# Apache Curator��Apache�Ŀ�Դ��Ŀ,��װ��zookeeper�Դ��Ŀͻ���,ʹ����Լ��
		<dependency>
			<groupId>org.apache.curator</groupId>
			<artifactId>curator-framework</artifactId>
			<version>4.1.0</version>
		</dependency>

	# ����DemoԴ��
		<dependency>
			<groupId>org.apache.curator</groupId>
			<artifactId>curator-examples</artifactId>
			<version>${curator-framework-version}</version>
		</dependency>

	# �ĵ�
		http://curator.apache.org/index.html
	


-------------------------------------
curator	 �ͻ��˵Ĵ���				 |
-------------------------------------

	// ���Բ���,1000���������������,���������3��
	RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
	// ֱ�Ӵ����ͻ���
	CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy)
	
	
	// ʹ��Builder����
	CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
		// ��ַ
		.connectString(host)
		// ���Բ���
		.retryPolicy(new ExponentialBackoffRetry(1000, 3))
		.connectionTimeoutMs(3000)
		.sessionTimeoutMs(3000)
		// Ȩ��
		.authorization(Arrays.asList(new AuthInfo("digest", "user:123456".getBytes())))
		.build();

	
	* Builder���ַ�ʽ����ȥ����Ȩ�ް�,ֻ������������
	* ������Ͽͻ��˺�Ҫִ��: start(); api

-------------------------------------
CuratorFramework					 |
-------------------------------------
	# ��Ӽ���
		// �������״̬����
		client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
			@Override
			public void stateChanged(CuratorFramework client, ConnectionState newState) {
				// �����Ƿ�����
				boolean isConnected = newState.isConnected();
			}
		});

		// ����¼�����
		client.getCuratorListenable().addListener(new CuratorListener() {
			@Override
			public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
				// ԭ����ܵ�event����
				WatchedEvent watchedEvent = event.getWatchedEvent();
			}
		});
