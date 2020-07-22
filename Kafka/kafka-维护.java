--------------------------------
kafka ά��						|
--------------------------------
	# ����Kafka
		kafka-server-start.bat ../../config/server.properties
	
	# �ر�
		kafka-server-stop.sh
	
	# ��������
		 bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
			--create
				* ����ָ��
			--zookeeper
				* ָ��zk�ĵ�ַ
			--partitions
				* �������ٸ�partition
			--replication-factor
				* ÿ��partition���ٸ�����
			--topic
				* ����topic������
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
			
			--if-not-exists
				* ��������Ѿ�����,�����׳��쳣,Ҳ���ᴴ���ɹ�
			
				
	# �鿴����������
		bin/kafka-topics.sh --zookeeper localhost:2181 --list
	
	# �鿴���������
		bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic test

		Topic:test      PartitionCount:1        ReplicationFactor:1     Configs:
        Topic: test     Partition: 0    Leader: 2       Replicas: 2     Isr: 2
	
	# ɾ������
		bin/kafka-topics.sh --zookeeper localhost:2181 --delete --topic test
		

	# �򿪻��ڿ���̨����Ϣ������	
		bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
			--broker-list
				* ָ����Ⱥ������ڵ�ĵ�ַ��Ϣ
			--topic
				* ָ��Ҫ���ĸ�topicд������
			

	# �򿪻��ڿ���̨����Ϣ������
		bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning
			--bootstrap-server
				* ָ����Ⱥ�нڵ����Ϣ
			--topic
				* ֪��Ҫ�����ĸ��ڵ�
			--from-beginning
				* �ò�����Ҫ��ͷ��ʼ����
			
	
	# �鿴topic�����ѽ���
		bin/kafka-run-class.sh kafka.tools.GetOffsetShell --broker-list localhost:9092 --topic test --time -1
			
			--time
				* -1 ��ʾ��ѯ��ǰtopic��������ǰ������Ϣoffset(��consumer��offset,������Ϣ��ÿ��������offset)
				* -2 ��ʾ��ȡ��ǰ������������Сλ��
