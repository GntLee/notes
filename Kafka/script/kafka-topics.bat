--------------------------------
kafka-topics.bat				|
--------------------------------
	# ��������
		 bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
			--zookeeper
				* ָ��zk
			--partitions
				* ��������
			--replication-factor
				* ÿ�������ĸ�������
			--topic
				* ��������,һ�㲻Ҫ���»��߿�ͷ,��Ϊkafka�ڲ�������ʹ���»��߿�ͷ
			--replica-assignment
				* �����Լ����Ʒ����ķ���
				* ���ַ�ʽ���ݷ����ŵ���ֵ��С���մ�С�����˳���������,���������֮���ö���","����
				* �����ڶ��������ð��":"����
				* ������ʹ�øò�����������ʱ����Ҫԭ���ر��� partitions �� replication-factor ����������
				* ͬһ�������ڵĸ����������ظ�,����ָ���� 0:0,1:1 ����,�ͻᱨ���쳣
				
				--replica-assignment 2:0,0:1,1:2,2:1

				2:0 ��ʾ�� 0 ������,����������,��broker.id Ϊ 2 �� 0 �Ľڵ���
				0:1 ��ʾ�� 1 ������,����������,��broker.id Ϊ 0 �� 1 �Ľڵ���
				1:2 ��ʾ�� 2 ������,����������,��broker.id Ϊ 1 �� 2 �Ľڵ���
				2:1 ��ʾ�� 3 ������,����������,��broker.id Ϊ 2 �� 1 �Ľڵ���

			--config
				* �Զ�������,���������Ĭ������
				* ����������Դ��ڶ��,��ʾ���Ƕ��ֵ
					--config kek=value
					--config cleanup.policy=compact --config max.message.bytes=l000
				* ������zookeeper�Ľڵ��²鿴��Щ����:
					get /config/topics/[topic-name]

			
			--if-not-exists
				* ��������Ѿ�����,�����׳��쳣,Ҳ���ᴴ���ɹ�
	
	# ����ͨ�� ZooKeeper �ͻ�������ȡ broker���������ķ������
		get /brokers/topics/[������]

		{"version":1,"partitions":{"2":[1,2]}}

		partitions:
			* ��ʾ��ǰ����ķ���"2",����������,��������border.id ���� 1 �� 2 �Ľڵ���
			* json�����key��ʾ����ķ������,value�����ʾ�÷����ĸ�������������Щbroker�ڵ���
				
		
		
	# �鿴��������
		bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic test
			--zookeeper
				* ָ��zk
			--describe
				* �鿴����ָ��
			--topic
				* ��������,�����ָ��,����ʾ�����е���������
				* Ҳ����ͬʱָ�������������,ʹ�ö��ŷָ�
			--topics-with-overrides
				* �ҳ����а����������õ�����,��ֻ���г��������뼯Ⱥ��һ�����õ�����
				* ע��ʹ�øò���ʱֻ��ʾԭ��ֻʹ�� describe ָ��ĵ�һ����Ϣ
			--under-replicated-partitions
				* �ҳ����а���ʧЧ�����ķ���
				* ����ʧЧ�����ķ����������ڽ���ͬ������,Ҳ�п���ͬ�������쳣
			--unavailable-partitions 
				* �鿴������û�� leader �����ķ���,��Щ����������������״̬
				* �������������ߺ���������˵���ڲ����õ�״̬
		
		Topic(������):test      PartitionCount(��������):1        ReplicationFactor(ÿ������������):1     Configs(�����������Ϣ):
        Topic: test     Partition(������): 0    Leader(��ǰ����Lader�������ڽڵ��broker.id): 2       Replicas(��ǰ�������и������ڽڵ��broker.id - AR): 2     Isr(��ǰ������ISR���� - ISR): 2
		
		Replicas
			* ��ǰ�������������Щ�ڵ���

	# �鿴����������������Ϣ
		bin/kafka-topics.sh --zookeeper localhost:2181 --list
	
	# �鿴��������ʱ���õĲ���(--config)
		get /config/topics/[������]

		{
			"version":1,
			"config":{
				"max.message.bytes":"10000",
				"cleanup.poliy":"compact"
			}
		}

		* config ��ʾ���õ�һ�����߶��������

	# replica�����㷨���ǻ���(0.10.x)
		* ��������һ������broker.rack˵����ǰbroker���ĸ�����
		* ����,�õ���ʱ����ȥ���
	
	# ���������ķ���
		* �������topicʱ,ָ����--replica-assignment����ʱ,�͸���ָ��������ȥ�����������

		* δָ�� --replica-assignment ����,��ʹ���ڲ��߼�ȥ�������

		* ʹ���ڲ��߼�����ʱ
		* ���������broker.rack,��ô��ʹ��ָ�������ķ������
		* ������нڵ㶼û���� broker.rack ����ʹ�� disabale-rack-awre ��������������,��ô���õľ���δָ�������ķ������
	
		
		*  δָ�������ķ������ʵ����
			AdminUtilities.assignReplicasToBrokersRackUnaware(�Ѿ���������,ʹ��AdminZkClient����)
		

	# �޸�����
		bin/kafka-topics.sh --zookeeper localhost:2181 --alter --topic [topic-name] --partitions 3
		bin/kafka-topics.sh --zookeeper localhost:2181 --alter --topic [topic-name] --config max.message.bytes=20000
		bin/kafka-topics.sh --zookeeper localhost:2181 --alter --topic [topic-name] --delete-config max.message.bytes

		--zookeeper
			* ָ��zk
		--alter
			* �޸������ָ��
		--topic
			* ָ��Ҫ�޸ĵ���������
		--if-exists
			* ���ִ���޸ĵ����ⲻ����,��ô�����׳��쳣
		
		--partitions
			* �޸ĵĲ���֮һ(�ò����޸�partition����)
			* �����ж������
		
		--config
			* �޸ĵ�����������Ϣ
			* ͨ���ò���ָ��һ�����߶������
		
		--delete-config
			* ɾ��֮ǰ���ǵ�����,ʹ��ָ�ԭ�е�Ĭ��ֵ
			* ͨ���ò���ָ��Ҫȡ�����ǵ���������
		
		* ĿǰKafkaֻ֧�����ӷ���������֧�ּ��ٷ�����
		* ���ҷ��������޸�,���ܻᵼ����Щ��key����Ϣ,Ͷ�ݵķ����ű�����(hash����,����partition�����ı���)
		
		* alter ָ����ʵ�Ѿ���ʱ��,�����Ժ��ɾ��,����ʹ��:kafka-configs.sh �ű���ʵ����ع���
	
	# ɾ������
		bin/kafka-topics.sh --zookeeper localhost:2181 --delete --topic [��������]

		* ���ⱻɾ����,���������ĴӴ���ɾ��,ֻ�Ǳ����Ϊɾ��
		* ���broker���õĲ���: delete.topic.enable=false ��ô�������������κ�Ч��
		* ���Ҫɾ���������� Kafka ���ڲ�����,��ôɾ��ʱ�ͻᱨ��

		* ʹ�� kafka-topics.sh �ű�ɾ���������Ϊ������ֻ���� ZooKeeper �еģ�admin/delete_topics ·���´���һ�����ɾ������ͬ���Ľڵ�
		* �Դ˱�Ǹ�����Ϊ��ɾ����״
		* �봴��������ͬ����,����ɾ������Ķ���Ҳ���� Kafka �Ŀ�����������ɵ�
		* ˵����,����ͨ������zookeeper����������ɾ������