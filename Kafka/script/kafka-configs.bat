-------------------------
kafka-configs.bat		 |
-------------------------
	# kafka-configs.bat �ű���ר�����������ý��в�����
		* ����Ĳ�����ָ������״̬���޸�ԭ�е�����,��˿��Դﵽ��̬�����Ŀ��
		* �ű������������ alter �Ͳ鿴���� describe ������ָ������
		* �ű�������õ�ԭ��һ��,�� ,ɾ,�ĵ���Ϊ�����Կ���������� 
		* �ű���������֧�ֲ���������ص�����,���� ��֧�ֲ��� broker,�û�,�Ϳͻ����� 3 �����͵�����
	
	bin/kafka-configs.bat --zookeeper localhost:2181 --describe --entity-type topics --entity-name topic-config
		
		--entity-type
			* ָ��Ҫ�޸ĵ�Ŀ��
				topics	�޸���������
				brokers	ָ��brokerldֵ,�� broker �� broker.id �������õ�ֵ
				clients ָ�� clientld ֵ,�� KatkaProducer �� KatkaConsumer �� client.id �������õ�ֵ
				users	ָ���û���

		--entity-name
			* ָ��Ŀ���ֵ