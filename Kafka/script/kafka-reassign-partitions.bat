----------------------------
kafka-reassign-partitions.sh|
----------------------------
	# ��������������·���
		bin/kafka-reassign-partitions.sh  --zookeeper localhost:2181  --generate --topics-to-move-json-file  reassign.json --broker-list 0,2
			* ���ɷ����ķ��䷽��

		bin/kafka-reassign-partitions.sh  --zookeeper localhost:2181  --execute --reassignment-json-file  project.json
			* ִ�з����ķ��䷽��

		bin/kafka-reassign-partitions.sh  --zookeeper localhost:2181  --verify --reassignment-json-file  project.json
			* �鿴ָ�������ķ������

			--generate
				* �ǵ�ǰ�ű���ָ��,��������һ�����·���ķ���

			--topics-to-move-json-file
				* ָ�������嵥JSON�ļ�

			--broker-list
				* ָ��Ҫ�����broker�ڵ��б�(broker.id)

			--execute
				* �ǵ�ǰ�ű���ָ��,����ִ��һ�����·���ķ���

			--reassignment-json-file
				* ָ�����䷽����JSON�ļ�
			
			--verify
				* �鿴ָ�������ķ������
			
			--throttle
				* ��ִ�з��������ʱ��,���Ƹ��������ݴ����ٶ�
				* byte/s