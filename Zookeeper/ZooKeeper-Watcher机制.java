
--------------------------------
Watcher ����					|
--------------------------------
	# ZK�ṩ��һ�ֶ��ķ����Ĺ��� Watcher
		* һ�Զ�,������ļ���һ������,�������б仯,��֪ͨ�����еĶ�����
	
	# ZK���¼�
		�ڵ�Ĵ���
		�ڵ���޸�
		�ڵ��ɾ��
		�ӽڵ�仯
	
	# Watcher���ƵĹ���
		1,�ͻ���������ע��Watcher
		2,������¼���������Watcher
		3,�ͻ��˻ص�Watcher�õ������¼����
	
	# Wather�����ص�
		* һ���Դ���
			* �¼����� ��������,һ�� watcher event �ͻᱻ�͵����ü����Ŀͻ���
			* ����Ч����һ���Ե�,�����ٴη�����ͬ���¼�,�����ٴδ���
		
		* �¼���װ
			* ZKʹ��WatcherEvent��������װ������¼�������
			* WatcheEvent ������ÿһ���¼���������������
				KeeperState ֪ͨ״̬
					@Deprecated
					Unknown (-1),
					Disconnected (0),
					@Deprecated
					NoSyncConnected (1),
					SyncConnected (3),
					AuthFailed (4),
					ConnectedReadOnly (5),
					SaslAuthenticated(6),
					Expired (-112);

				EventType �¼�����
					None (-1),
					NodeCreated (1),
					NodeDeleted (2),
					NodeDataChanged (3),
					NodeChildrenChanged (4);

				Path �ڵ�·��
					
			
		* event�첽����
			* Watcher֪ͨ�¼��ӷ���˷��͵��ͻ������첽��
		
		* ��ע���ٴ���
			* �ͻ��˱������ڷ����ע���˼���,�����յ�����˵��¼�����֪ͨ
		

	# ֪ͨ״̬���¼�����
		SyncConnected
			|-None
				*  �ͻ��������˳ɹ���������
			|-NodeCreated
				* �����Ľڵ㱻����
			|-NodeDeleted
				* �����Ľڵ㱻ɾ��
			|-NodeDataChanged
				* �����ڵ�����ݷ����޸�
			|-NodeChildrenChanged
				* �����ڵ���ӽڵ㷢���ı�
			
		Disconnected
			|-None
				* �ͻ�����Zookeeper����˶Ͽ�����
		Expired
			|-None
				* �Ự��ʱ
				* ͨ��Ҳ���յ� SessionExpiredException �쳣
		AuthFailed
			|-None
				* һ�����������
					1,ʹ�ô���� schema ����Ȩ��У��
					2,SASL Ȩ�޼��ʧ��
				* ͬʱҲ���յ� AuthFailedException
		
		* ����״̬�¼�(EventType=None,Path=null),����Ҫ�ͻ�������ע��
		* �ͻ���ֻҪ����Ҫ,ֱ�Ӵ�����



