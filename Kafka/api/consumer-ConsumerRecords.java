--------------------------------
ConsumerRecords<K,V>				|
--------------------------------
	# ���������ѵ���Ϣ����ļ���
	# ��ʵ���˵�����:Iterable<ConsumerRecord<K, V>,��ʾ���ConsumerRecord
	# ��̬����
		ConsumerRecords<Object, Object> EMPTY = new ConsumerRecords<>(Collections.EMPTY_MAP);

	# ��̬����
		<K, V> ConsumerRecords<K, V> empty()

	# ʵ������
		int count();
			* ��Ϣ������

		boolean isEmpty();
			* �Ƿ�Ϊ��

		Iterator<ConsumerRecord<K, V>> iterator();
			* ���ذ�����������Ϣ�ĵ�����

		Set<TopicPartition> partitions();
			* ���ص�ǰ������Ϣ���ڵ����������Լ�����

		Iterable<ConsumerRecord<K, V>> records(String topic);
			* ���ذ�����ָ��������Ϣ�ĵ�����
			* ��ǰ�����߿��ܶ����˶������

		List<ConsumerRecord<K, V>> records(TopicPartition partition);
			* ���ذ�����ָ������,ָ��������Ϣ�ļ���
			* ��ǰ�����߿��ܶ����˶������,���߶������

