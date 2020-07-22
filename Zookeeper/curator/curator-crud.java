
----------------------------------------
crud									|
----------------------------------------
	# �ܽ�
		* �������﷨:����().options1().option2(value)..optionN().forPath("/");
			curatorFramework.start();
			curatorFramework.create().forPath("/");
			curatorFramework.getChildren().forPath("/");
			curatorFramework.getACL().forPath("/");
			curatorFramework.setACL().withACL(null).forPath("/");
			curatorFramework.delete().forPath("/");
			curatorFramework.setData().forPath("/");
			curatorFramework.sync().forPath("/");
			curatorFramework.checkExists().forPath("/");

		* ���ݲ����Ĳ�ͬ,������N���options

	# ��ͨ����
		client.create().forPath(String path, byte[] data);
		
		*  Ĭ�ϴ���������,�����л��ڵ�

	# ��������ģʽ�Ľڵ�ڵ�
		client.create().withMode(CreateMode.EPHEMERAL).forPath(String path, byte[] data);
	
	# �����ܱ����Ľڵ�
		String client.create().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(String path, byte[] data);

		* δ֪
	
	#  �ݹ�Ĵ����༶�ڵ�
		//TODO

	# �޸Ľڵ�
		client.setData().forPath(String path, byte[] data);
	
	# �첽�޸Ľڵ�
		CuratorListener listener = new CuratorListener(){
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
            }
        };
		// ����ȫ�ֵ� CuratorListener
        client.getCuratorListenable().addListener(listener);

		// inBackground ��ʾ�첽����,�޸Ľ����֪ͨ��ȫ�ֵ�CuratorListener
        client.setData().inBackground().forPath(String path, byte[] data);

	
	# �ص��޸Ľڵ�
		// ������ʱ�Ļص��ӿ�
		BackgroundCallback backgroundCallback = new BackgroundCallback() {
			@Override
			public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
				
			}
		};
		// inBackground ��ʾ�첽����,�޸Ľ����֪ͨ����ʱ��Callback
		client.setData().inBackground(backgroundCallback).forPath(String path, byte[] data);
	
	# ɾ���ڵ�
		client.delete().forPath(String path);
	

	# ����ɾ��
		client.delete().guaranteed().forPath(String path);

		* ��Ϊ������������,ɾ����������ʧ��,�������ڵ�������صĽڵ�Ļ�,���ܻ��ƻ�������
		* һ��ִ���˱���ɾ��,��ôCurator����¼ɾ��ʧ�ܵĽڵ�,������ɾ������,ֱ���ɹ�Ϊֹ
		* ɾ��ʧ��ʱ�Ի�����쳣,����ֻҪCuratorFrameworkʵ���򿪾ͻ᳢��ɾ��
	
	# ��ȡ�ӽڵ�
		List<String> client.getChildren().watched().forPath(String path);
	
	# ��ȡ�ڵ�,�������watcher
		
		List<String> client.getChildren().usingWatcher(new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				//zk��watcher
			}
		}).forPath(String path);
	
	# ��ȡ�ڵ���Ϣ,���Ҽ���
		byte[] data = curatorFramework.getData().usingWatcher(new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				
			}
		}).forPath(String path);
	

	# �ݹ��ɾ��ָ���ڵ�
		client.delete().deletingChildrenIfNeeded().forPath(String path);
