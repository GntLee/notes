------------------------------------
kafka-preferred-replica-election.sh	|
------------------------------------
	# �ֶ�ִ�����ȸ�����leaderѡ�ٽű�

	kafka-preferred-replica-election.sh --zookeeper localhost:2181	
		
		--path-to-json-file
			* ����ͨ���ò���ָ��һ��JSON�ļ�
			* ��JSON�ļ�������Ҫִ�����ȸ���ѡ�ٵ�parition�嵥(������ִ�����з���)
				{
					"paritions":[{
						"parition":0,
						"topic":"topic-paritions"
					},{
						"parition":1,
						"topic":"topic-paritions"
					}]
				}